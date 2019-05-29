package thomas.nill.testdaten.basis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;

import thomas.nill.antlr4.script.scriptBaseVisitor;
import thomas.nill.antlr4.script.scriptLexer;
import thomas.nill.antlr4.script.scriptParser;

public class Skript extends scriptBaseVisitor<String> {
	private ResourceCreatorFabric fabric = null;
	private Values values = new Values();
	private ValueCreator<?> delegateValueCreator;

	public Skript(String name) {
		super();
		fabric = new ResourceCreatorFabric(name);
	}

	public Values getValues(Values values) {
		return fabric.getValues(values,this);
	}

	public String auswerten(String expression) {
		scriptLexer lexer = new scriptLexer(new ANTLRInputStream(expression));
		scriptParser parser = new scriptParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.script();
		return visit(tree);
	}

	@Override
	public String visitTextsentence(scriptParser.TextsentenceContext ctx) {
		return getText(ctx.TEXT()) ;
	}

	@Override
	public String visitRsentence(scriptParser.RsentenceContext ctx) {
		TerminalNode op = ctx.TEXT();
		System.out.println("op= " + ctx.TEXT());
		if (op != null && ctx.sentence() != null) {
			String arg = visitSentence(ctx.sentence());
			System.out.println("arg= " + arg);
			if (arg != null) {
				return executeOp(op.getText(), arg);
			}
		}
		return "visitRsentence";
	}

	@Override
	public String visitNormalSentence(scriptParser.NormalSentenceContext ctx) {
		System.out.println("visitNormalSentence= " + ctx.getText());
		String nachfolger = "";
		if (ctx.listsentence() != null && !ctx.listsentence().isEmpty()) {
			
			nachfolger =  visitListsentence(ctx.listsentence()) ;
		}
		if (ctx.rsentence() != null && ctx.rsentence().sentence() != null) {
			System.out.println("visitRsentence= " + ctx.rsentence().getText());
			return visitRsentence(ctx.rsentence()) + nachfolger;
		}
		if (ctx.gsentence() != null && !ctx.gsentence().isEmpty()) {
			System.out.println("visitGsentence= " + ctx.gsentence().getText());
			return visitGsentence(ctx.gsentence()) + nachfolger;
		}
		if (ctx.textsentence() != null && !ctx.textsentence().isEmpty()) {
			System.out.println("textsentence= " + ctx.textsentence().getText());
			return visitTextsentence(ctx.textsentence()) + nachfolger;
		}
		return "visitNormalSentence";
	}

	@Override
	public String visitSentence(scriptParser.SentenceContext ctx) {
		System.out.println("visitSentence= " + ctx.getText());
		if (ctx.constructorCall() != null) {
			return visitConstructorCall(ctx.constructorCall());
		}
		if (ctx.normalSentence() != null) {
			return getText(ctx.OTHERS(0)) + visitNormalSentence(ctx.normalSentence())  + getText(ctx.OTHERS(1));
		}
		return "visitSentence";
	}

	private String getText(TerminalNode node){
		return (node== null) ? "" : node.getText();
	}


	@Override
	public String visitConstructorCall(scriptParser.ConstructorCallContext ctx) {
		if (delegateValueCreator == null) {
			delegateValueCreator = (ValueCreator<?>) createValueConstructor(ctx);
		}
		return "" + delegateValueCreator.generateValue(values);
	}

	private Object createValueConstructor(scriptParser.ConstructorCallContext ctx) {
		Class<?> clazz = null;
		try {
			String className = ctx.TEXT().getText();
			String[] args = buildConstructorArgumentList(ctx);
			clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			try {
				return searchConstructor(clazz, args);

			} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
				throw new TestdatenException("The Class " + clazz + " has not a constructor for " + args.length
						+ " arguments or an argument and the class of the argument did not match");
			}
		} catch (ClassNotFoundException e) {
			throw new TestdatenException("The Class " + clazz + " does not exist");
		}
	}

	private Object searchConstructor(Class<?> clazz, String[] args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		for (Constructor<?> ctor : clazz.getConstructors()) {
			final Class<?>[] ctorParams = ctor.getParameterTypes();
			if (ctorParams.length == args.length) {
				Object oargs[] = new Object[args.length];
				boolean canCorrected = correctTypesOfObjects(oargs, args, ctorParams);
				if (canCorrected) {
					try {
						return ConstructorUtils.invokeConstructor(clazz, oargs);
					} catch (NoSuchMethodException e) {
						throw new TestdatenException("No Constructor found", e);
					}
				}
			}
		}

		throw new TestdatenException("No Constructor found");
	}

	private boolean correctTypesOfObjects(Object[] oargs, String[] args, final Class<?>[] ctorParams) {
		for (int n = 0; n < ctorParams.length; n++) {
			String className = ctorParams[n].getSimpleName();
			switch (className) {
			case "int":
			case "Integer":
				oargs[n] = Integer.valueOf(args[n].toString());
				break;
			case "long":
			case "Long":
				oargs[n] = Long.valueOf(args[n].toString());
				break;
			case "String":
				oargs[n] = Long.valueOf(args[n].toString());
				break;
			default:
				return false;
			}
		}
		return true;
	}

	private String[] buildConstructorArgumentList(scriptParser.ConstructorCallContext ctx) {
		List<TerminalNode> argList = ctx.constructorArgs().TEXT();
		String args[] = new String[argList.size()];
		int i = 0;
		for (TerminalNode node : argList) {
			args[i] = node.getText();
			i++;
		}
		return args;
	}

	@Override
	public String visitListsentence(scriptParser.ListsentenceContext ctx) {
		StringBuffer buffer = new StringBuffer();
		if (ctx.sentence() != null) {

			List<scriptParser.SentenceContext> liste = ctx.sentence();
			for (scriptParser.SentenceContext c : liste) {
				buffer.append(visitSentence(c));
			}
		}
		return buffer.toString();
	}

	@Override
	public String visitGsentence(scriptParser.GsentenceContext ctx) {
		if (ctx.sentence() != null) {
			String name = visitSentence(ctx.sentence());
			ValueCreator<?> c = fabric.searchCreator(name, this);
			return c.generateValue(values).toString();
		}
		return "visitGsentence";
	}

	private String executeOp(String op, String arg) {
		switch (op) {
		case "firstChar":
			return arg.substring(0, 1);
		case "upper":
			return arg.toUpperCase();
		case "lower":
			return arg.toLowerCase();
		default:
			throw new TestdatenException("operation " + op + " is not defined");
		}
	}
}
