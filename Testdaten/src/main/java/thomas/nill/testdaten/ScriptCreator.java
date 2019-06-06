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

/**
 * Creates a value with a script language
 * @author tnill
 * <p>
 * This {@link ValueCreator} is used in {@link ResourceCreatorFabric}
 * In the resource properties file for every name is a short script.
 * <p>
 * An example:
 * <p>
 * {@literal provider=t-online.de|gmx.de|web.de|hotmail.de|yahoo.de}<br>
 * {@literal sex=m|w}<br>
 * {@literal firstname_w = Luise| Lotte|Susanne|Emmy|Sonja|}<br>
 * {@literal firstname_m = Manfred|Thomas|Ralf|Carl Friedrich|Hermann|Bernhard|Leonhard|David}<br>
 * {@literal firstname={firstname_{sex}}}<br>
 * {@literal name={title}{firstname} {lastname}}<br>
 * {@literal email={firstname}.{lastname}@{provider}}<br>
 * <p>
 * the {@literal provider=t-online.de|gmx.de|web.de|hotmail.de|yahoo.de}<br>
 * create a instance of {@link StringListCreator}. The same for
 * {@literal sex=m|w} and firstname_w, firstname_m
 * <p>
 * firstname={firstname_{sex}} is a little script, it refers with
 * {sex} to the sex array. If sex=w then it look in the array
 * firstname_w for a name. If sex=m it look in the array firstname_m.
 * <p> 
 * {@literal name={title}{firstname} {lastname}}<br>
 * describes a simple concatenation.
 * <p>
 * {@literal email={firstname}.{lastname}@{provider}|firstChar({firstname}).{lastname}@{provider}}<br>
 * is a switch with | as seperator between
 * <p>
 * {@literal email={firstname}.{lastname}@{provider}}<br>
 * and<br>
 * {@literal email=firstChar({firstname}).{lastname}@{provider}}<br>
 * <p>
 * The firstChar is a function applies to {@literal {firstname}}
 * <p>
 * Constructor calls look like:
 * <p>
 * {@literal initDistribution_lastname=thomas.nill.testdaten.DistributionCreator[thomas.nill.testdaten.random.ArrayDistribution 0.3 0.2 0.2 0.2 0.1]}<br>
 * {@literal initDistribution_streetName=thomas.nill.testdaten.DistributionFunctionCreator[thomas.nill.testdaten.random.GauﬂDistribution 10 5.0 2.0]}<br>
 * <p>
 * for distributions that are use in lastname, streetName. The initDistribution_
 * is a hint, that distributions will be instantiated.
 * <p>
 * For the creation of sub bean, are the constructors
 * <p>
 * {@literal addAnschreiben=thomas.nill.testdaten.BeanListCreator[tests.Anschreiben 5]}<br>
 * 
 * @see thomas.nill.testdaten.basis.ValueCreator
 *
 */
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
