package thomas.nill.testdaten;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.antlr4.script.scriptBaseVisitor;
import thomas.nill.antlr4.script.scriptLexer;
import thomas.nill.antlr4.script.scriptParser;
import thomas.nill.testdaten.basis.ConstructorHelper;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;


@Slf4j
public class ScriptCreator extends scriptBaseVisitor<Object> implements ValueCreator<Object>{
	private ResourceCreatorFabric fabric = null;
	private Values values = null;
	private ValueCreator<?> delegateValueCreator;
	private String expression;

	public ScriptCreator(String name,String expression,ResourceCreatorFabric fabric) {
		super();
		this.fabric = fabric;
	
		this.expression = expression;
	}

	public Object auswerten(String expression) {
		if (values == null) {
			values = new Values();
		};
		scriptLexer lexer = new scriptLexer(new ANTLRInputStream(expression));
		scriptParser parser = new scriptParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.script();
		return visit(tree);
	}
	
	@Override
	public Object generateValue(Values v) {
		values = v;
		return auswerten(expression);
	}

	@Override
	public Object visitTextsentence(scriptParser.TextsentenceContext ctx) {
		return getText(ctx.TEXT()) ;
	}

	@Override
	public Object visitRsentence(scriptParser.RsentenceContext ctx) {
		TerminalNode op = ctx.TEXT();
		log.debug("op= " + ctx.TEXT());
		if (op != null && ctx.sentence() != null) {
			String arg = visitSentence(ctx.sentence()).toString();
			log.debug("arg= " + arg);
			if (arg != null) {
				return executeOp(op.getText(), arg);
			}
		}
		return "visitRsentence";
	}

	@Override
	public Object visitNormalSentence(scriptParser.NormalSentenceContext ctx) {
		log.debug("visitNormalSentence= " + ctx.getText());
		String nachfolger = "";
		if (ctx.listsentence() != null && !ctx.listsentence().isEmpty()) {
			nachfolger =  visitListsentence(ctx.listsentence()).toString();
		}
		if (ctx.rsentence() != null && ctx.rsentence().sentence() != null) {
			log.debug("visitRsentence= " + ctx.rsentence().getText());
			return visitRsentence(ctx.rsentence()) + nachfolger;
		}
		if (ctx.gsentence() != null && !ctx.gsentence().isEmpty()) {
			log.debug("visitGsentence= " + ctx.gsentence().getText());
			return visitGsentence(ctx.gsentence()) + nachfolger;
		}
		if (ctx.textsentence() != null && !ctx.textsentence().isEmpty()) {
			log.debug("textsentence= " + ctx.textsentence().getText());
			return visitTextsentence(ctx.textsentence()) + nachfolger;
		}
		return "visitNormalSentence";
	}

	@Override
	public Object visitSentence(scriptParser.SentenceContext ctx) {
		log.debug("visitSentence= " + ctx.getText());
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
	public Object visitConstructorCall(scriptParser.ConstructorCallContext ctx) {
		if (delegateValueCreator == null) {
			delegateValueCreator = (ValueCreator<?>) createValueConstructor(ctx);
		}
		return  delegateValueCreator.generateValue(values);
	}

	private Object createValueConstructor(scriptParser.ConstructorCallContext ctx) {
		Class<?> clazz = null;
		try {
			String className = ctx.TEXT().getText();
			String[] args = buildConstructorArgumentList(ctx);
			clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			try {
				return new ConstructorHelper().searchConstructorAndCreate(clazz, args);

			} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
				throw new TestdataException("The Class " + clazz + " has not a constructor for " + args.length
						+ " arguments or an argument and the class of the argument did not match");
			}
		} catch (ClassNotFoundException e) {
			throw new TestdataException("The Class " + clazz + " does not exist");
		}
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
	public Object visitListsentence(scriptParser.ListsentenceContext ctx) {
		StringBuffer buffer = new StringBuffer();
		if (ctx.sentence() != null) {

			List<scriptParser.SentenceContext> liste = ctx.sentence();
			for (scriptParser.SentenceContext c : liste) {
				buffer.append(visitSentence(c).toString());
			}
		}
		return buffer.toString();
	}

	@Override
	public Object visitGsentence(scriptParser.GsentenceContext ctx) {
		if (ctx.sentence() != null) {
			String name = visitSentence(ctx.sentence()).toString();
			ValueCreator<?> c = fabric.searchCreator(name);
			return c.generateValue(values);
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
			throw new TestdataException("operation " + op + " is not defined");
		}
	}
}
