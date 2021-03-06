// Generated from script.g4 by ANTLR 4.0

    package thomas.nill.antlr4.script;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class scriptLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, TEXT=8, OTHERS=9, 
		WS=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'('", "')'", "'{'", "'['", "'|'", "'}'", "']'", "TEXT", "OTHERS", "WS"
	};
	public static final String[] ruleNames = {
		"T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "TEXT", "OTHERS", 
		"WS"
	};


	public scriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "script.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 9: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\f\66\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\6\t\'\n\t\r\t\16\t(\3\n\6\n,\n\n\r\n\16\n-\3\13\6\13"+
		"\61\n\13\r\13\16\13\62\3\13\3\13\2\f\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r"+
		"\b\1\17\t\1\21\n\1\23\13\1\25\f\2\3\2\5\16\60\60\62;C\\aac|\u00c6\u00c6"+
		"\u00d8\u00d8\u00de\u00de\u00e1\u00e1\u00e6\u00e6\u00f8\u00f8\u00fe\u00fe"+
		"\6\"#./==BB\4\13\f\17\178\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\3\27\3\2\2\2\5\31\3\2\2\2\7\33\3\2\2\2\t\35\3\2\2\2\13\37"+
		"\3\2\2\2\r!\3\2\2\2\17#\3\2\2\2\21&\3\2\2\2\23+\3\2\2\2\25\60\3\2\2\2"+
		"\27\30\7*\2\2\30\4\3\2\2\2\31\32\7+\2\2\32\6\3\2\2\2\33\34\7}\2\2\34\b"+
		"\3\2\2\2\35\36\7]\2\2\36\n\3\2\2\2\37 \7~\2\2 \f\3\2\2\2!\"\7\177\2\2"+
		"\"\16\3\2\2\2#$\7_\2\2$\20\3\2\2\2%\'\t\2\2\2&%\3\2\2\2\'(\3\2\2\2(&\3"+
		"\2\2\2()\3\2\2\2)\22\3\2\2\2*,\t\3\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-"+
		".\3\2\2\2.\24\3\2\2\2/\61\t\4\2\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2"+
		"\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\b\13\2\2\65\26\3\2\2\2\6\2(-\62";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}