// URL that generated this code:
// http://txt2re.com/index-java.php3?s=%231%20@%201,3:%204x4&4&5&6&7&8 

import java.util.regex.*;

class Main
{
	public static void main(String[] args)
	{
		//String txt="#1 @ 1,3: 4x4";
		String txt="#1386 @ 604,534: 28x27";
		
		String re1=".*?";	// Non-greedy match on filler
		String re2="(\\d+)";	// Integer Number 1
		String re3=".*?";	// Non-greedy match on filler
		String re4="(\\d+)";	// Integer Number 2
		String re5=".*?";	// Non-greedy match on filler
		String re6="(\\d+)";	// Integer Number 3
		String re7=".*?";	// Non-greedy match on filler
		String re8="(\\d+)";	// Integer Number 4
		String re9=".*?";	// Non-greedy match on filler
		String re10="(\\d+)";	// Integer Number 5
		
		Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find())
		{
			String int1=m.group(1);
			String int2=m.group(2);
			String int3=m.group(3);
			String int4=m.group(4);
			String int5=m.group(5);
			System.out.print("("+int1.toString()+")"+"("+int2.toString()+")"+"("+int3.toString()+")"+"("+int4.toString()+")"+"("+int5.toString()+")"+"\n");
		}
	}
}

//-----
// This code is for use with Sun's Java VM - see http://java.sun.com/ for downloads. 
//
// Paste the code into a new java application or a file called 'Main.java'
//
// Compile and run in Unix using:
// # javac Main.java 
// # java Main 
//