/* Generated By:JavaCC: Do not edit this line. Karloff.java */
import java.io.*;
import java.util.ArrayList;

class Tipo {
  String tipo;

  Tipo(String tipo){
    this.tipo = tipo;
  }

  public String toString() {
    return this.tipo;
  }
}

class Fator {}

class True extends Fator {
  String value = "True";

  public String toString() {
    return this.value;
  }
}

class False extends Fator {
  String value = "False";

  public String toString() {
    return this.value;
  }
}

class Num extends Fator {
  String num;

  Num(String num) {
    this.num = num;
  }

  public String toString() {
    return this.num;
  }
}

class Variavel extends Fator{
  String id;

  Variavel(String id){
    this.id = id;
  }

  public String toString() {
    return this.id;
  }
}

class FuncaoCall extends Fator{
  String id;
  ArrayList<Expressao> listaExp;

  FuncaoCall(String id, ArrayList<Expressao> listaExp){
    this.id = id;
    this.listaExp = listaExp;
  }

  public String toString() {
    String bloco = this.id + "(";

    if (!this.listaExp.isEmpty()) {

      Expressao first = this.listaExp.remove(0);
      bloco = bloco.concat(first.toString());

      for (Expressao exp : this.listaExp) {
        bloco = bloco.concat("," + exp.toString());
      }

      listaExp.add(0, first);
    }

    bloco = bloco + ")";

    return bloco;
  }
}

class Operador {
  String op;

  Operador(String op){
    this.op = op;
  }

  public String toString() {
    switch (this.op) {
      case "&":
        return "and";
      case "|":
        return "or";
      default:
        return this.op;
    }
  }
}

class Expressao {}

class Operacao extends Expressao {
  Operador operador;
  Expressao expEsq,expDir;

  Operacao(Operador operador, Expressao expEsq, Expressao expDir){
    this.operador = operador;
    this.expEsq = expEsq;
    this.expDir = expDir;
  }

  public String toString() {
    return this.expEsq.toString() + " " + this.operador.toString() + " " + this.expDir.toString();
  }
}

class FinalExpressao extends Expressao {
  Fator fator;

  FinalExpressao(Fator fator){
    this.fator = fator;
  }

  public String toString() {
    return this.fator.toString();
  }
}

class Comando {
  int indent = 0;

  public void setIndent(int indent){
    this.indent = indent;
  }

  public int getIndent() {
    return indent;
  }

  public String getTabs() {
    String tabs = "";

    for (int i = 0; i < this.indent; i++){
      tabs = tabs.concat("\u005ct");
    }

    return tabs;
  }
}

class Atrib extends Comando {
  String id;
}

class AtribExp extends Atrib {
  Expressao exp;

  AtribExp(String id, Expressao exp) {
    this.id = id;
    this.exp = exp;
  }

  public String toString() {
    return this.getTabs() + this.id + " = " + this.exp.toString() + "\u005cn";
  }
}

class AtribInput extends Atrib {
  AtribInput(String id) {
    this.id = id;
  }

  public String toString() {
    return this.getTabs() + this.id + " = input()\u005cn";
  }
}

class If extends Comando {
  Expressao teste;
  ArrayList<Comando> comandos;

  If(Expressao teste, ArrayList<Comando> comandos){
    this.teste = teste;
    this.comandos = comandos;
  }

  public String toString() {

    String bloco = this.getTabs() + "if " + this.teste.toString() + ":\u005cn";

    for (Comando comando : this.comandos) {
      comando.setIndent(this.getIndent() + 1);
      bloco = bloco.concat(comando.toString());
    }

    if (!bloco.endsWith("\u005cn")){
      bloco = bloco + "\u005cn";
    }

    return bloco;
  }
}

class While extends Comando {
  Expressao teste;
  ArrayList<Comando> comandos;

  While(Expressao teste, ArrayList<Comando> comandos){
    this.teste = teste;
    this.comandos = comandos;
  }

  public String toString() {
    String bloco = this.getTabs() + "while " + this.teste.toString() + ":\u005cn";

    for (Comando comando : this.comandos) {
      comando.setIndent(this.getIndent() + 1);
      bloco = bloco.concat(comando.toString());
    }

    if (!bloco.endsWith("\u005cn")){
      bloco = bloco + "\u005cn";
    }

    return bloco;
  }
}

class Repeat extends Comando {
  Expressao teste;
  ArrayList<Comando> comandos;

  Repeat(Expressao teste, ArrayList<Comando> comandos){
    this.teste = teste;
    this.comandos = comandos;
  }

  public String toString() {
    String bloco = this.getTabs() + "while not " + this.teste.toString() + ":\u005cn";

    for (Comando comando : this.comandos) {
      comando.setIndent(this.getIndent() + 1);
      bloco = bloco.concat(comando.toString());
    }

    if (!bloco.endsWith("\u005cn")){
      bloco = bloco + "\u005cn";
    }

    return bloco;
  }
}

class Return extends Comando {
  Expressao exp;

  Return(Expressao exp){
    this.exp = exp;
  }

  public String toString() {
    return this.getTabs() + "return " + this.exp.toString() + "\u005cn";
  }
}

class Print extends Comando {
  Expressao exp;

  Print(Expressao exp){
    this.exp = exp;
  }

  public String toString() {
    return this.getTabs() + "print(" + this.exp.toString() + ")\u005cn";
  }
}

class FuncaoCallCom extends Comando {
  FuncaoCall funcaoCall;

  FuncaoCallCom(FuncaoCall funcaoCall){
    this.funcaoCall = funcaoCall;
  }

  public String toString() {
    return this.getTabs() + this.funcaoCall.toString() + "\u005cn";
  }
}

class Main {
  ArrayList<Variavel> variaveis;
  ArrayList<Comando> comandos;

  Main(ArrayList<Variavel> variaveis, ArrayList<Comando> comandos){
    this.variaveis = variaveis;
    this.comandos = comandos;
  }

  public String toString() {
    String bloco = "def main():\u005cn";

    for (Comando comando : this.comandos) {
      comando.setIndent(1);
      bloco = bloco.concat(comando.toString());
    }

    if (!bloco.endsWith("\u005cn")){
      bloco = bloco + "\u005cn";
    }

    return bloco;
  }
}

class Argumento {
  String nome;
  Tipo tipo;

  Argumento(String nome, Tipo tipo){
    this.nome = nome;
    this.tipo = tipo;
  }

  public String toString() {
    return this.nome.toString() + ": " + tipo.toString();
  }
}

class Funcao {
  String nome;
  Tipo tipo_retorno;
  ArrayList<Argumento> argumentos;
  ArrayList<Variavel> variaveis;
  ArrayList<Comando> comandos;

  Funcao(String nome, Tipo tipo_retorno, ArrayList<Argumento> argumentos, ArrayList<Variavel> variaveis, ArrayList<Comando> comandos){
    this.nome = nome;
    this.tipo_retorno = tipo_retorno;
    this.argumentos = argumentos;
    this.variaveis = variaveis;
    this.comandos = comandos;
  }

  public String toString() {
    String bloco = "def " + this.nome + "(";

    if (!this.argumentos.isEmpty()) {

      Argumento first = this.argumentos.remove(0);
      bloco = bloco.concat(first.toString());

      for (Argumento argumento : this.argumentos) {
        bloco = bloco.concat("," + argumento.toString());
      }

      argumentos.add(0, first);
    }

    bloco = bloco.concat(") -> " + this.tipo_retorno.toString() + ":\u005cn");

    for (Comando comando : this.comandos) {
      comando.setIndent(1);
      bloco = bloco.concat(comando.toString());
    }

    if (!bloco.endsWith("\u005cn")){
      bloco = bloco + "\u005cn";
    }

    return bloco;
  }
}

class ArvoreKarloff {
  Main main;
  ArrayList<Funcao> funcoes;

  ArvoreKarloff(Main main, ArrayList<Funcao> funcoes) {
    this.main = main;
    this.funcoes = funcoes;
  }

  public String toString() {
    String bloco = "#!/usr/bin python3\u005cn\u005cn" + this.main.toString() + "\u005cn";

    for (Funcao funcao : this.funcoes) {
      bloco = bloco.concat(funcao.toString() + "\u005cn");
    }

    bloco = bloco + "if __name__ == \u005c"__main__\u005c":\u005cn\u005ctmain()\u005cn";

    return bloco;
  }
}

public class Karloff implements KarloffConstants {
  public static void main(String args[]) throws Exception {
    FileInputStream fs;
    String filename = "script.py";

    switch (args.length) {
      case 0:
        throw new Exception("There is no enough params. Template: java Karloff <source_filename.kar> <target_filename.py>");
      case 1:
        // abrir o arquivo passado por linha
        // de comando contento o código em Karloff:
        fs = new FileInputStream(new File(args[0]));
        break;
      case 2:
        // abrir o arquivo passado por linha
        // de comando contento o código em Karloff:
        fs = new FileInputStream(new File(args[0]));
        // pega nome do arquivo a ser gerado
        filename = new String(args[1]);
        break;
      default:
        throw new Exception("Too much params. Template: java Karloff <source_filename.kar> <target_filename.py>");
    }
    // Instanciar o parser da linguagem Karloff passando
    // como argumento o arquivo contendo o código
    //Karloff a ser processado:
    Karloff parser = new Karloff(fs);
    // Chamar a primeira regra do parser que irá
    // analisar o código e devolver a árvore sintática
    ArvoreKarloff arvore = parser.Karloff();
    // passar a árvore para o gerador de código
    // que deve gerar um arquivo .java (ou outra linguagem) com o mesmo
    //nome do arquivo de entrada
    geraCodigo(arvore, filename);
  }

  public static void geraCodigo(ArvoreKarloff prog, String filename) throws Exception {
    // System.out.println(filename + "\n\n" + prog.toString());
    File file = new File(filename);

    boolean result;

    result = file.createNewFile();  //creates a new file  

    if(result) {
      System.out.println("file created " + file.getCanonicalPath()); //returns the path string  
    } else  {
      System.out.println("File already exist at location: " + file.getCanonicalPath());
    }

    FileOutputStream outputStream = new FileOutputStream(file);
    outputStream.write(prog.toString().getBytes());
  }

// KARLOFF -> MAIN FUNC?
  static final public ArvoreKarloff Karloff() throws ParseException {
  Main main;
  ArrayList<Funcao> funcoes = new ArrayList();
    main = MainC();
    Func(funcoes);
    jj_consume_token(0);
   {if (true) return new ArvoreKarloff(main, funcoes);}
    throw new Error("Missing return statement in function");
  }

// MAIN -> "void" "main" "(" ")" "{" VARDECL SEQCOMANDOS "}"
  static final public Main MainC() throws ParseException {
  ArrayList<Variavel> variaveis = new ArrayList();
  ArrayList<Comando> comandos = new ArrayList();
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    jj_consume_token(APAR);
    jj_consume_token(FPAR);
    jj_consume_token(ACHAVES);
    VarDecl(variaveis);
    SeqComandos(comandos);
    jj_consume_token(FCHAVES);
    {if (true) return new Main(variaveis, comandos);}
    throw new Error("Missing return statement in function");
  }

// VARDECL -> VARDECL "newVar" TIPO TOKEN_id ";" | vazio
  static final public void VarDecl(ArrayList<Variavel> variaveis) throws ParseException {
  Token t;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEWVAR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(NEWVAR);
      Tipo();
      t = jj_consume_token(ID);
                           variaveis.add(new Variavel(t.image));
      jj_consume_token(SEMICOLON);
    }
  }

// TIPO -> "integer" | "bool"
  static final public Tipo Tipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TBOOL:
      jj_consume_token(TBOOL);
           {if (true) return new Tipo("bool");}
      break;
    case TINT:
      jj_consume_token(TINT);
                                              {if (true) return new Tipo("int");}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

// SEQCOMANDOS -> SEQCOMANDOS COMANDO | vazio
  static final public void SeqComandos(ArrayList<Comando> comandos) throws ParseException {
  Comando c;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CIF:
      case CWHILE:
      case CREP:
      case CRET:
      case CPRINT:
      case ID:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      c = Comando();
                comandos.add(c);
      jj_consume_token(SEMICOLON);
    }
  }

// COMANDO -> TOKEN_id "=" EXP ";"
// | TOKEN_id "(" LISTAEXP? ")" ";"
// | "if" "(" EXP ")" "then" "{" SEQCOMANDOS "}" ";"
// | "while" "(" EXP ")" "{" SEQCOMANDOS "}" ";"
// | "repeat" "{" SEQCOMANDOS "}" "until" "(" EXP ")"
// | "return" EXP ";"
// | "System.output" "(" EXP ")" ";"
// | TOKEN_id "=" "System.readint" "(" ")" ";"
  static final public Comando Comando() throws ParseException {
  Token t;
  Comando c;
  Expressao teste;
  Expressao exp;
  ArrayList<Comando> comandos = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      t = jj_consume_token(ID);
      c = ComID(t);
     {if (true) return c;}
      break;
    case CIF:
      jj_consume_token(CIF);
      jj_consume_token(APAR);
      teste = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(CTHEN);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
     {if (true) return new If(teste, comandos);}
      break;
    case CWHILE:
      jj_consume_token(CWHILE);
      jj_consume_token(APAR);
      teste = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
     {if (true) return new While(teste, comandos);}
      break;
    case CREP:
      jj_consume_token(CREP);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
      jj_consume_token(CUNT);
      jj_consume_token(APAR);
      teste = Exp();
      jj_consume_token(FPAR);
     {if (true) return new Repeat(teste, comandos);}
      break;
    case CRET:
      jj_consume_token(CRET);
      exp = Exp();
     {if (true) return new Return(exp);}
      break;
    case CPRINT:
      jj_consume_token(CPRINT);
      jj_consume_token(APAR);
      exp = Exp();
      jj_consume_token(FPAR);
     {if (true) return new Print(exp);}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Comando ComID(Token t) throws ParseException {
  Comando c;
  ArrayList<Expressao> listaExp = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CATRIB:
      jj_consume_token(CATRIB);
      c = ComIDAtrib(t);
     {if (true) return c;}
      break;
    case APAR:
      jj_consume_token(APAR);
      ListaExp(listaExp);
      jj_consume_token(FPAR);
     {if (true) return new FuncaoCallCom(new FuncaoCall(t.image, listaExp));}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Comando ComIDAtrib(Token t) throws ParseException {
  Expressao exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case APAR:
    case BTRUE:
    case BFALSE:
    case ID:
    case NUM:
      exp = Exp();
     {if (true) return new AtribExp(t.image, exp);}
      break;
    case CREADINT:
      jj_consume_token(CREADINT);
      jj_consume_token(APAR);
      jj_consume_token(FPAR);
                              {if (true) return new AtribInput(t.image);}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

// EXP -> "(" EXP OP EXP ")" | FATOR
  static final public Expressao Exp() throws ParseException {
  Expressao expEsq, expDir;
  Operador op;
  Fator f;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case APAR:
      jj_consume_token(APAR);
      expEsq = Exp();
      op = Op();
      expDir = Exp();
      jj_consume_token(FPAR);
     {if (true) return new Operacao(op, expEsq, expDir);}
      break;
    case BTRUE:
    case BFALSE:
    case ID:
    case NUM:
      f = Fator();
     {if (true) return new FinalExpressao(f);}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

// FATOR -> TOKEN_id | TOKEN_id "(" LISTAEXP? ")"
// | TOKEN_numliteral |
// "true" | "false"
  static final public Fator Fator() throws ParseException {
  Token t;
  ArrayList<Expressao> listaExp = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case APAR:
        jj_consume_token(APAR);
        ListaExp(listaExp);
        jj_consume_token(FPAR);
             {if (true) return new FuncaoCall(t.image, listaExp);}
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
     {if (true) return new Variavel(t.image);}
      break;
    case NUM:
      t = jj_consume_token(NUM);
      {if (true) return new Num(t.image);}
      break;
    case BTRUE:
      jj_consume_token(BTRUE);
             {if (true) return new True();}
      break;
    case BFALSE:
      jj_consume_token(BFALSE);
              {if (true) return new False();}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

// OP -> "+" | "-" | "*" | "/" | "&" | "|" | "<" | ">" | "=="
  static final public Operador Op() throws ParseException {
 Token t;
    t = jj_consume_token(OP);
          {if (true) return new Operador(t.image);}
    throw new Error("Missing return statement in function");
  }

// LISTAEXP -> EXP | LISTAEXP "," EXP
  static final public void ListaExp(ArrayList<Expressao> listaExp) throws ParseException {
  Expressao exp;
    exp = Exp();
               listaExp.add(exp);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      ListaExpL(listaExp);
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
  }

  static final public void ListaExpL(ArrayList<Expressao> listaExp) throws ParseException {
  Expressao exp;
    jj_consume_token(COMMA);
    exp = Exp();
                     listaExp.add(exp);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      ListaExpL(listaExp);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
  }

// FUNC -> FUNC "func" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}"
// | "func" TIPO TOKEN_id "(" LISTAARG? ")" "{" VARDECL SEQCOMANDOS "}"
  static final public void Func(ArrayList<Funcao> funcoes) throws ParseException {
  Tipo tipo_retorno;
  Token t;
  ArrayList<Argumento> argumentos = new ArrayList();
  ArrayList<Variavel> variaveis = new ArrayList();
  ArrayList<Comando> comandos = new ArrayList();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNC:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_3;
      }
      jj_consume_token(FUNC);
      tipo_retorno = Tipo();
      t = jj_consume_token(ID);
      jj_consume_token(APAR);
      ListaArg(argumentos);
      jj_consume_token(FPAR);
      jj_consume_token(ACHAVES);
      VarDecl(variaveis);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
     funcoes.add(new Funcao(t.image, tipo_retorno, argumentos, variaveis, comandos));
    }
  }

// LISTAARG -> TIPO TOKEN_id | LISTAARG "," TIPO TOKEN_id
  static final public void ListaArg(ArrayList<Argumento> argumentos) throws ParseException {
  Token t;
  Tipo tipo;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TINT:
      case TBOOL:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
      tipo = Tipo();
      t = jj_consume_token(ID);
                         argumentos.add(new Argumento(t.image, tipo));
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        tipo = Tipo();
        t = jj_consume_token(ID);
                                                                                                     argumentos.add(new Argumento(t.image, tipo));
      }
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public KarloffTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800,0xc000,0x20da0000,0x20da0000,0x10200,0x67000200,0x66000200,0x200,0x66000000,0x2000,0x2000,0x8000000,0xc000,0x2000,};
   }

  /** Constructor with InputStream. */
  public Karloff(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Karloff(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new KarloffTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Karloff(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new KarloffTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Karloff(KarloffTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(KarloffTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[31];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 31; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
