// Generated from c:\Users\Admin\OneDrive\Escritorio\Carpetas\Facultad\Compilador2021\src\main\java\compiladores2021\compiladornuevo.g4 by ANTLR 4.8
 
package compiladores2021;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class fileLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PA=1, PC=2, LA=3, LC=4, WHILE=5, AND=6, OR=7, IGUAL=8, MENOR=9, MAYOR=10, 
		SUMA=11, RESTA=12, MULT=13, DIVISION=14, ENTERO=15, PCOMA=16, TIPO_DATO=17, 
		COMA=18, ID=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PA", "PC", "LA", "LC", "WHILE", "AND", "OR", "IGUAL", "MENOR", "MAYOR", 
			"SUMA", "RESTA", "MULT", "DIVISION", "ENTERO", "PCOMA", "TIPO_DATO", 
			"COMA", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'while'", "'&&'", "'||'", "'='", "'<'", 
			"'>'", "'+'", "'-'", "'*'", "'/'", null, "';'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PA", "PC", "LA", "LC", "WHILE", "AND", "OR", "IGUAL", "MENOR", 
			"MAYOR", "SUMA", "RESTA", "MULT", "DIVISION", "ENTERO", "PCOMA", "TIPO_DATO", 
			"COMA", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public fileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "compiladornuevo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25j\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\3\17\3\17\3\20\6\20M\n\20\r\20\16\20N\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22`\n\22\3\23"+
		"\3\23\3\24\3\24\7\24f\n\24\f\24\16\24i\13\24\2\2\25\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25\3\2\5\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\2m\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\67\3"+
		"\2\2\2\17:\3\2\2\2\21=\3\2\2\2\23?\3\2\2\2\25A\3\2\2\2\27C\3\2\2\2\31"+
		"E\3\2\2\2\33G\3\2\2\2\35I\3\2\2\2\37L\3\2\2\2!P\3\2\2\2#_\3\2\2\2%a\3"+
		"\2\2\2\'c\3\2\2\2)*\7*\2\2*\4\3\2\2\2+,\7+\2\2,\6\3\2\2\2-.\7}\2\2.\b"+
		"\3\2\2\2/\60\7\177\2\2\60\n\3\2\2\2\61\62\7y\2\2\62\63\7j\2\2\63\64\7"+
		"k\2\2\64\65\7n\2\2\65\66\7g\2\2\66\f\3\2\2\2\678\7(\2\289\7(\2\29\16\3"+
		"\2\2\2:;\7~\2\2;<\7~\2\2<\20\3\2\2\2=>\7?\2\2>\22\3\2\2\2?@\7>\2\2@\24"+
		"\3\2\2\2AB\7@\2\2B\26\3\2\2\2CD\7-\2\2D\30\3\2\2\2EF\7/\2\2F\32\3\2\2"+
		"\2GH\7,\2\2H\34\3\2\2\2IJ\7\61\2\2J\36\3\2\2\2KM\t\2\2\2LK\3\2\2\2MN\3"+
		"\2\2\2NL\3\2\2\2NO\3\2\2\2O \3\2\2\2PQ\7=\2\2Q\"\3\2\2\2RS\7k\2\2ST\7"+
		"p\2\2T`\7v\2\2UV\7f\2\2VW\7q\2\2WX\7w\2\2XY\7d\2\2YZ\7n\2\2Z`\7g\2\2["+
		"\\\7e\2\2\\]\7j\2\2]^\7c\2\2^`\7t\2\2_R\3\2\2\2_U\3\2\2\2_[\3\2\2\2`$"+
		"\3\2\2\2ab\7.\2\2b&\3\2\2\2cg\t\3\2\2df\t\4\2\2ed\3\2\2\2fi\3\2\2\2ge"+
		"\3\2\2\2gh\3\2\2\2h(\3\2\2\2ig\3\2\2\2\6\2N_g\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}