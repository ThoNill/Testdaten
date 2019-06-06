// Generated from script.g4 by ANTLR 4.0

    package thomas.nill.antlr4.script;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class scriptBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements scriptVisitor<T> {
	@Override public T visitSentence(scriptParser.SentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitListsentence(scriptParser.ListsentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitNormalSentence(scriptParser.NormalSentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitConstructorArgs(scriptParser.ConstructorArgsContext ctx) { return visitChildren(ctx); }

	@Override public T visitGsentence(scriptParser.GsentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitRsentence(scriptParser.RsentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitConstructorCall(scriptParser.ConstructorCallContext ctx) { return visitChildren(ctx); }

	@Override public T visitTextsentence(scriptParser.TextsentenceContext ctx) { return visitChildren(ctx); }

	@Override public T visitScript(scriptParser.ScriptContext ctx) { return visitChildren(ctx); }
}