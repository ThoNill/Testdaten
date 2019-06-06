// Generated from script.g4 by ANTLR 4.0

    package thomas.nill.antlr4.script;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface scriptListener extends ParseTreeListener {
	void enterSentence(scriptParser.SentenceContext ctx);
	void exitSentence(scriptParser.SentenceContext ctx);

	void enterListsentence(scriptParser.ListsentenceContext ctx);
	void exitListsentence(scriptParser.ListsentenceContext ctx);

	void enterNormalSentence(scriptParser.NormalSentenceContext ctx);
	void exitNormalSentence(scriptParser.NormalSentenceContext ctx);

	void enterConstructorArgs(scriptParser.ConstructorArgsContext ctx);
	void exitConstructorArgs(scriptParser.ConstructorArgsContext ctx);

	void enterGsentence(scriptParser.GsentenceContext ctx);
	void exitGsentence(scriptParser.GsentenceContext ctx);

	void enterRsentence(scriptParser.RsentenceContext ctx);
	void exitRsentence(scriptParser.RsentenceContext ctx);

	void enterConstructorCall(scriptParser.ConstructorCallContext ctx);
	void exitConstructorCall(scriptParser.ConstructorCallContext ctx);

	void enterTextsentence(scriptParser.TextsentenceContext ctx);
	void exitTextsentence(scriptParser.TextsentenceContext ctx);

	void enterScript(scriptParser.ScriptContext ctx);
	void exitScript(scriptParser.ScriptContext ctx);
}