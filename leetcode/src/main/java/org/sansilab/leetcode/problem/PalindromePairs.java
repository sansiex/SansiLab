package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//https://leetcode.com/problems/palindrome-pairs/
public class PalindromePairs {
	public static void main(String[] args){
		PalindromePairs sln=new PalindromePairs();
		List<String[]> list = Lists.newArrayList(
				new String[]{"bat", "tab", "cat"}
				, new String[]{"abcd", "dcba", "lls", "s", "sssll"}
				, new String[]{"faaj","dhjd","idfhffgcaijhaedadc","dbgcdhjfhfhfaigicfc","cebagfdeiiajaa","fcdbfhajjgfeea","djgeegabijcigha","dedg","a","gdgabgdcgefa","ijigcgdbgebhdaf","ee","jdjbbeddcbeibaih","b","bhhibjjgjdc","ghfjfe","ae","adffjfjcjdcggjcafha","cfabdedhieg","cceeccge","bbg","caifeijjchcbfg","feebcijdgbiagebjefa","icdbg","bhbdg","bia","dhifefcdabgdb","iagcgdfgfhdaeebbfb","jffjffahceggdigachce","eac","dadcfcdfijjghedch","cei","aa","daggbgdhigjjdbfd","cchacjbadejeaibe","ggajabi","chjebd","hacdibhc","bjai","gaehci","aebjgiidia","igchic","fficghhdhcghadbbbefi","cch","biejbaahdiicfidjfaf","ahhc","ffdjgcc","he","fccajjhj","jccabdedieiabj","dcahhdehagegeejcgh","afbch","hefgjcbffghfe","jbadeaggaec","behaf","ciaegedacbafbjjbhcbf","age","jjabadbhchfgjgghibgi","gbaehjafeahe","dhhgjdgiaijbjbgefhha","gaaa","fcbfbibjgb","jfbaeebafedhef","daagaebbffjeahce","igihigghebcehccg","abia","jbfihjcfibgc","ijigefjgge","geiafaegdededd","caadhigjaegbehac","jjjagicgaec","igfefjfhij","cbgcacifd","f","ibbbagj","hjcchhdejdiebbg","eieeaghdbifggjc","jjbdbd","aghdiahf","gfjbgcdheiaahigae","caachh","fahdaaiceeh","hdbedciceafegbjcf","djgcjccafbigidhi","aaehhdh","bcchec","jagcjbagdhcbabie","jiffeifc","gehifhheebae","jbddaeeacecjj","cihjhdf","fhacjbdjaiidaj","iebahehehjefjehegcf","hdjabcgba","icf","ijhfjhjca","hgabdjgchbadh","cggg","ibdcab","ac","cbfehcidebhchbj","cgheeabbidhj","di","ehbd","bhfciideic","fhididi","ibfgcfeiigh","fcicf","fachghiacghdhccfecb","faidijcggcdjighh","hegdha","deefagah","jbbbhfjiaihdghighiab","fbbdaighfeg","egaf","cbjbcea","gbhdfdiiijbda","cijiagbiaiggdbhjb","igedjhbiaijaa","gfjeciidgjc","hdac","ddhjjehffjagaijeie","hhchdcaaijbhbcejgdfj","aifie","gbijhbedfhjbihe","chfijgciaijg","eb","fibbegecjhjjibifjj","gdieddifjahccihgjfde","ceiae","hfhiedjda","g","ciafghacge","gffdjaaddegighfgfcdj","gaibij","ghhhjejachfic","ca","abhc","gjdjijaiaejgfbdibfj","gbcijcbbebibdhafhjih","eegibhbbiaifcgae","gi","ceffbhgicib","fddha","chejedai","fffdciiegheifafbg","gdhdabjedddgjjjediic","d","fhbffgfiaeeghddba","bbacgffgjc","jfde","i","beecdhef","hcdefjeeiie","ege","iahf","jadbecbfh","edfjifjidbahd","ja","e","baddb","jihagdigbje","dedecc","ddahbaihjdabedahaja","ffbiadej","fdfjbcehfheibcfif","chb","ffbcccjjfbbfadiaijj","idhhaecfahifbddgibbc","hb","dibjhfdbafaiif","dcj","bccehbcdaeigabjjib","hacgg","afjifbjfd","ccgeijdb","gidegda","acieihafebicfcgi","faiicicefc","ehfajhg","gibbdbjbabde","fhd","aicifdggfjbeaah","jeegdgfj","id","hahfaaaea","dbgeijffeigdacfjia","ajfbhggbjhchjhagdh","iibiidbej","ehcjcjfbcgi","eadadbb","abbbj","ffjeagificjjcjea","ddjiiadgjajghfabh","eggbgagdffehgebe","ceehieadcejff","caccbacacdgbbijac","hbbjedaceafbbighdadd","dcehggeeghad","hefcgdgdeadcjb","dfj","geccbigghcacgdebg","hfdfaagdfcehjehi","jdacdaaibccbchgjc","jb","dhbdccbechhbahjacc","gcbhddddh","hccf","aihhbfhegfde","c","fheieaiede","bghjajggbdhieh","edbdbehcbgfg","aadbbegggbafh","cdbbhgdjcc","hjfjbajhcjhhjbdicbha","diejiaiaabeje","ebibfcbjcejig","ijieccicgghj","igiddg","ehjfbaehfiaeedfe","fjihi","biehedfigjecchjjga","ijijceibdaiieicaed","feceabidhhbibbgheieg","jijag","ggcgdeejij","fhfjabjjfegfacchaghj","cccaijdi","bbhidfecc","abjdefheb","aaccjgjfacj","egcjbcidfabchhdcd","fieafcjfcfc","egbgdjgbgafhabeie","dgfhga","ab","ggibdfiig","ijaichcihaccibhada","fjjhabhhggg","ediibbhddi","deieccagc","fdiafifac","jadjgaad","j","cjecfb","hc","bijdjgfjbgcgf","highdijjdf","bdcjbaghfi","ddigdegdhjeeb","ed","gfcddeihbaaj","efceifjg","djf","hfggfjcjigbhjeefjg","ce","jibcfjhgadfjijhgijcj","fcigf","bdagehabbjgaice","abfjhcdjfaihhbfbfc","dg","bdgjggdbhaeeg","agfi","abfhfdibgj","heggdf","gceghcghf","aggjhihjhcfaifeh","jidafccbehd","gdgcdehdbjihijedj","fiejddgcjebidgb","cfcijfjbejfbdd","idcjjdcacbggjgff","eih","eifeba","bbeigaiaaegggedj","dahe","jdefaigic","dfefcf","bbcicddjghjdgeeidaja","gifchejfedce","bfdhbid","cbg","dfhihfdj","acidgh","h","acdbcggbgfjbiaacded","fddfhgj","cfgccdeeaebcfbd","fabhdigaiccejibjceag","adgghgceg","ggdeebhff","cifcaadfab","dagedbcaibjfbbaeihei","bfjfjfehfh","dahhbadagieahdcebfij","hgiecbi","dfc","dfcceeebggbfhghhgja","eijaa","bgcjbbdjagcdjbgaeji","faha","aj","dibbabgi","ghig","aijegdejcbhcjba","egc","dajgaia","gjggjbdedg","gehadeabdbicggff","agii","dhbgjc","hiaachecfdjdfdbh","efbdic","debecjfcid","dfgcbjiccd","cgiijae","ffiiagg","fcefcfih","aedifagbcfbc","afhaaacfidh","iejgfggcigcgd","bcjj","hjedaifabgeefa","icdgcceii","fffdacgbbbggidaaih","dahcdfe","fibegidhiaijiajbac","eggfahc","iffciggeffhgig","hcajhihgjjbdajhgi","gijicei","ababgbccgjffdchjdd","ffgecjiafiigc","fbjjdf","igfiijidif","bibfcgbbcefjfah","fababdggcbacjaifi","iaaiijbcadc","fed","hdabhcjidifdcba","jhaefdgfbfeh","jbfjjecbj","abbbacidgh","bdefged","bacgihjdigjffcf","ijbihaejgcajfdcb","agba","ggdiajjbebbd","eighdiejij","cgffj","giihfchggecehgfb","cidcjgifhjabfhef","dihihdej","idb","daa","ddgcejahdc","dhjbghfjeccaccejfc","cfdcbbadibaieifighia","bcbcegiiedfdgbhhi","dfeadcfjdagfdd","hidfiec","gaeiffdcebdcjeeci","fhacgeibe","cdeg","ejcjhdhaieddjdch","fbhiciadfeigbibdcabg","cgejdcbjgbaj","hbagjegecidjabiia","iajjhgig","edhdgdjfcee","aabfibgifhide","jhbjdighihgfajj","gieahieabeaceigbdh","jghgea","cgeebee","icdfaaiiecb","aac","fhhhfjcdbgbghce","hdgefdda","egdiihjjifafgcdcghj","gfidegfaceij","idhghffdadgg","fgdd","ejcaieae","fcadjcdbjhadac","adgdhibef","adhgfhaf","fhfhfihaai","ccjfajcah","ajfh","hjcecgafh","bh","bbiaieaacacfh","ijebgahecfaidefjcdgg","ihhigij","ijiiieacbbffija","ihehifhihe","cbaiebbiffjdh","caecjceieifafa","cfeahfe","iehcehfgd","giichabjhgggc","idacgeibgbih","adajfafgi","ggjcdjhag","bccgacbib","fbggjcfjccfjcjfdjde","fcbfhcd","cbdaecd","jjidajdaadfijgi","jfcgjceca","cahbgjceihg","ficfdcgbjgaccdh","jacgcfiegcd","jajfaeiaghhfgce","dfcgjjhjaejbcbjeca","dahjdfgddbihag","ieegiifhjeidj","bgiaehahc","bgeejdbdjfjaaiedba","hfjjfhdhcefb","ahhdbhdbegdcdcehddf","cbihafh","cgcjfifijjbajcghfgce","djgfhj","ffhbbggdabeibceig","dejiejfgeieedbff","chedj","eaab","ibfhgdaedga","dgiggiccad","jcc","ijjhaagagejicihfg","idibbgeiif","ga","jjcfbegjbg","iadfcid","aheccafdjceaih","eaeaggbjejeghbgdb","cbfjdfbe","ecfegghjhcbgbcbciie","eddijfbicdhce","cijbicdgedejd","if","hfebih","jeejbgfcdhhf","cddhfhg","deaa","eiechie","gh","ghhiaaddbjbefj","icijhcfcffjheeji","aajfjjeefjfbcgfgc","ijajdffahb","cifgbcjjgejag","aifjehjjhchcfcgd","cebdahhjbccfib","jg","hcie","bjjjaf","bgbibefaafcehc","ahcbaebh","geaeddi","hhb","cedcgcaedegfbfacbbij","iihfaaich","eaecibgadagjdgge","ehbfaicehcibaabfggc","jeabbiaeh","hhcach","cjjchcdch","jdgbfgcecagd","aea","chhjhbfj","eadibff","hccgjgaabcabajgahbbd","bf","fjd","affhhjcgdjbjcgjcb","hhbej","fijfjhjifbbeigg","bhdjhfebdgiggdeici","eddcagi","ddh","gjjddehcajgeh","ehdjaeabgdajjbeabd","gggihedg","bajibfeibejbea","fiaggibjjgficd","jfhidccgdfgahfh","heafhcdigeifhbaegae","hffahjaiihafhaaf","gcegdddgfbb","fbedjbhfbfhhfaejcbj","ddfhghfbd","hejejjhdbbfieejdjdbc","eigbichggbeafchd","dgcahcjfjfgi","dfbiafaeefbfia","ibca","chaidcd","gaheaijbhfhjhfiid","jihgcbfef","hchcgf","hcbbdjehfdaagjdef","eha","caif","dbcaijgd","ihabceadifjc","hfdj","jcahf","icdiefgje","ibeiehbejcaiicgcajaf","ig","adfcf","chaghcgggic","deedcbb","ibjefhhcd","ie","cbhbbicihabhf","hjg","gbdccfhbcafccbieafce","afcfe","jgb","dghgijiiebag","jdfhceicihcgidggee","ddaciehfjaediggia","ghfgidghb","cdichich","hhhbifffa","hhfdhjegfjccgeedjb","dahcchiidicfeagiefie","hd","gibhidfjfci","edihdgagebdbc","fbgdjb","cbfjhjbbfifcafbafid","bedafijjcjjfebggb","ieccgeebfcbhagifeg","iiafhbbjhighef","gfgiiadjb","dgeeiaefc","ebdbc","ibidehficicijcij","eedbe","idhccj","jdaiiba","bcgjd","jcede","hgddjedfcadej","jigca","hgbbf","gcjbjibfahgaiaij","jfiecdeg","hbbecgcchhhbhchdhdi","jefghdjieedhbha","aggfaiebgifiajf","jciiifafchjjf","iicfdbc","echgajhibcdhfccdhhcd","hfaaifbdb","hjibifhhfbechbhaeji","efhjg","ddbchahcijjfhcjedfhc","ffg","ibdaidadfaa","gfdifaiiecd","fciihhebcjdacfjjia","aifdbcjjfjaidbhh","ahg","hhghfj","bjfcgegi","bbfibgbiicghdbeabbai","ffjhig","dheefechicecid","fggcihdeda","hfdibcdcgibcfhggeh","fgjja","higbijafjcii","ijichhdiehg","hhebcd","jjdfaggbdegb","aiegibdcgcb","aajiegb","gifehjiebdbacjf","ejgdhgdadei","diighceeehbb","cigffddgggeahh","bgfhejjhia"}
				, new String[]{"a", ""}
				, new String[]{"a","abc","aba",""}
		);
		for (String[] input:list) {
			long s=System.currentTimeMillis();
			Object output=sln.palindromePairs(input);
			long e=System.currentTimeMillis();
			System.out.println(e-s+" ms");
			System.out.println(JsonUtils.toJson(output));
		}

	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		Map<String, Integer> match = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			char[] chars=new char[words[i].length()];
			for (int j = 0; j < words[i].length(); j++) {
				chars[j] = words[i].charAt(words[i].length()-1-j);
			}
			String rev = new String(chars);
			match.put(rev, i);
		}
		for (int i = 0; i < words.length; i++) {
			String w=words[i];
			for (int j = 0; j <= w.length(); j++) {
				String head=w.substring(0,j);
				String tail=w.substring(j);
				Integer o1 = match.get(head);
				if (o1!=null && i!=o1) {
					if (j==w.length()) {
						List<Integer> pair = new ArrayList<Integer>(2);
						pair.add(i);
						pair.add(o1);
						list.add(pair);
					} else if (j<w.length() && isPalindrome(w, j, w.length()-1)) {
						List<Integer> pair = new ArrayList<Integer>(2);
						pair.add(i);
						pair.add(o1);
						list.add(pair);
					}
				}

				Integer o2 = match.get(tail);
				if (o2!=null && i!=o2) {
					if (j==w.length() && isPalindrome(w, 0, j-1)) {
						List<Integer> pair = new ArrayList<Integer>(2);
						pair.add(o2);
						pair.add(i);
						list.add(pair);
					} else if (j>0 && j<w.length() && isPalindrome(w, 0, j-1)) {
						List<Integer> pair = new ArrayList<Integer>(2);
						pair.add(o2);
						pair.add(i);
						list.add(pair);
					}
				}
			}
		}
		return list;
	}

	public boolean isPalindrome(String str, int s, int e){
		for (int i = 0; i < 1+(e-s)/2; i++) {
			if (str.charAt(i+s) != str.charAt(e-i)) {
				return false;
			}
		}
		return true;
	}

}

