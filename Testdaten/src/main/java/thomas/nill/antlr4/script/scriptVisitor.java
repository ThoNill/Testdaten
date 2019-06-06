// Generated from script.g4 by ANTLR 4.0

    package thomas.nill.antlr4.script;

import org.antlr.v4.runtime.tree.*;

public interface scriptVisitor<T> extends ParseTreeVisitor<T> {
	T visitSentence(scriptParser.SentenceContext ctx);

	T visitListsentence(scriptParser.ListsentenceContext ctx);

	T visitNormalSentence(scriptParser.NormalSentenceContext ctx);

	T visitConstructorArgs(scriptParser.ConstructorArgsContext ctx);

	T visitGsentence(scriptParser.GsentenceContext ctx);

	T visitRsentence(scriptParser.RsentenceContext ctx);

	T visitConstructorCall(scriptParser.ConstructorCallContext ctx);

	T visitTextsentence(scriptParser.TextsentenceContext ctx);

	T visitScript(scriptParser.ScriptContext ctx);
}