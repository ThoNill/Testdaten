// Generated from script.g4 by ANTLR 4.0

    package thomas.nill.antlr4.script;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class scriptParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, TEXT=6, OTHERS=7, WS=8;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'{'", "'|'", "'}'", "TEXT", "OTHERS", "WS"
	};
	public static final int
		RULE_script = 0, RULE_sentence = 1, RULE_normalSentence = 2, RULE_constructorCall = 3, 
		RULE_constructorArgs = 4, RULE_listsentence = 5, RULE_gsentence = 6, RULE_textsentence = 7, 
		RULE_rsentence = 8;
	public static final String[] ruleNames = {
		"script", "sentence", "normalSentence", "constructorCall", "constructorArgs", 
		"listsentence", "gsentence", "textsentence", "rsentence"
	};

	@Override
	public String getGrammarFileName() { return "script.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public scriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); sentence();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(19); match(4);
				setState(20); sentence();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentenceContext extends ParserRuleContext {
		public NormalSentenceContext normalSentence() {
			return getRuleContext(NormalSentenceContext.class,0);
		}
		public List<TerminalNode> OTHERS() { return getTokens(scriptParser.OTHERS); }
		public TerminalNode OTHERS(int i) {
			return getToken(scriptParser.OTHERS, i);
		}
		public ConstructorCallContext constructorCall() {
			return getRuleContext(ConstructorCallContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentence);
		int _la;
		try {
			setState(34);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26); constructorCall();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				_la = _input.LA(1);
				if (_la==OTHERS) {
					{
					setState(27); match(OTHERS);
					}
				}

				setState(30); normalSentence();
				setState(32);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(31); match(OTHERS);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NormalSentenceContext extends ParserRuleContext {
		public ListsentenceContext listsentence() {
			return getRuleContext(ListsentenceContext.class,0);
		}
		public GsentenceContext gsentence() {
			return getRuleContext(GsentenceContext.class,0);
		}
		public RsentenceContext rsentence() {
			return getRuleContext(RsentenceContext.class,0);
		}
		public TextsentenceContext textsentence() {
			return getRuleContext(TextsentenceContext.class,0);
		}
		public NormalSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterNormalSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitNormalSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitNormalSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NormalSentenceContext normalSentence() throws RecognitionException {
		NormalSentenceContext _localctx = new NormalSentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_normalSentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(36); gsentence();
				}
				break;

			case 2:
				{
				setState(37); rsentence();
				}
				break;

			case 3:
				{
				setState(38); textsentence();
				}
				break;
			}
			setState(41); listsentence();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorCallContext extends ParserRuleContext {
		public ConstructorArgsContext constructorArgs() {
			return getRuleContext(ConstructorArgsContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(scriptParser.TEXT, 0); }
		public ConstructorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterConstructorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitConstructorCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitConstructorCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorCallContext constructorCall() throws RecognitionException {
		ConstructorCallContext _localctx = new ConstructorCallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constructorCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(TEXT);
			setState(44); constructorArgs();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorArgsContext extends ParserRuleContext {
		public TerminalNode TEXT(int i) {
			return getToken(scriptParser.TEXT, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(scriptParser.TEXT); }
		public ConstructorArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterConstructorArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitConstructorArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitConstructorArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorArgsContext constructorArgs() throws RecognitionException {
		ConstructorArgsContext _localctx = new ConstructorArgsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constructorArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(1);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEXT) {
				{
				{
				setState(47); match(TEXT);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53); match(2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListsentenceContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ListsentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listsentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterListsentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitListsentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitListsentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListsentenceContext listsentence() throws RecognitionException {
		ListsentenceContext _localctx = new ListsentenceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_listsentence);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(55); sentence();
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GsentenceContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public GsentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gsentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterGsentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitGsentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitGsentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GsentenceContext gsentence() throws RecognitionException {
		GsentenceContext _localctx = new GsentenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_gsentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(3);
			setState(62); sentence();
			setState(63); match(5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextsentenceContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(scriptParser.TEXT, 0); }
		public TextsentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textsentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterTextsentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitTextsentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitTextsentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextsentenceContext textsentence() throws RecognitionException {
		TextsentenceContext _localctx = new TextsentenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_textsentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RsentenceContext extends ParserRuleContext {
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public TerminalNode OTHERS() { return getToken(scriptParser.OTHERS, 0); }
		public TerminalNode TEXT() { return getToken(scriptParser.TEXT, 0); }
		public RsentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rsentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).enterRsentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof scriptListener ) ((scriptListener)listener).exitRsentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof scriptVisitor ) return ((scriptVisitor<? extends T>)visitor).visitRsentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RsentenceContext rsentence() throws RecognitionException {
		RsentenceContext _localctx = new RsentenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rsentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(TEXT);
			setState(69);
			_la = _input.LA(1);
			if (_la==OTHERS) {
				{
				setState(68); match(OTHERS);
				}
			}

			setState(71); match(1);
			setState(72); sentence();
			setState(73); match(2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\nN\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t"+
		"\t\4\n\t\n\3\2\3\2\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\5\3\37\n\3"+
		"\3\3\3\3\5\3#\n\3\5\3%\n\3\3\4\3\4\3\4\5\4*\n\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\7\6\63\n\6\f\6\16\6\66\13\6\3\6\3\6\3\7\7\7;\n\7\f\7\16\7>\13\7"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\5\nH\n\n\3\n\3\n\3\n\3\n\3\n\2\13\2\4"+
		"\6\b\n\f\16\20\22\2\2M\2\24\3\2\2\2\4$\3\2\2\2\6)\3\2\2\2\b-\3\2\2\2\n"+
		"\60\3\2\2\2\f<\3\2\2\2\16?\3\2\2\2\20C\3\2\2\2\22E\3\2\2\2\24\31\5\4\3"+
		"\2\25\26\7\6\2\2\26\30\5\4\3\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2"+
		"\2\31\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2\2\34%\5\b\5\2\35\37\7\t\2\2"+
		"\36\35\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 \"\5\6\4\2!#\7\t\2\2\"!\3\2\2"+
		"\2\"#\3\2\2\2#%\3\2\2\2$\34\3\2\2\2$\36\3\2\2\2%\5\3\2\2\2&*\5\16\b\2"+
		"\'*\5\22\n\2(*\5\20\t\2)&\3\2\2\2)\'\3\2\2\2)(\3\2\2\2*+\3\2\2\2+,\5\f"+
		"\7\2,\7\3\2\2\2-.\7\b\2\2./\5\n\6\2/\t\3\2\2\2\60\64\7\3\2\2\61\63\7\b"+
		"\2\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2"+
		"\2\2\66\64\3\2\2\2\678\7\4\2\28\13\3\2\2\29;\5\4\3\2:9\3\2\2\2;>\3\2\2"+
		"\2<:\3\2\2\2<=\3\2\2\2=\r\3\2\2\2><\3\2\2\2?@\7\5\2\2@A\5\4\3\2AB\7\7"+
		"\2\2B\17\3\2\2\2CD\7\b\2\2D\21\3\2\2\2EG\7\b\2\2FH\7\t\2\2GF\3\2\2\2G"+
		"H\3\2\2\2HI\3\2\2\2IJ\7\3\2\2JK\5\4\3\2KL\7\4\2\2L\23\3\2\2\2\n\31\36"+
		"\"$)\64<G";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}