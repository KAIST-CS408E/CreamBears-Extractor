package kr.dogfoot.hwplib.tool.paragraphadder;

import java.util.ArrayList;

import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.charshape.CharPositonShapeIdPair;
import kr.dogfoot.hwplib.object.bodytext.paragraph.charshape.ParaCharShape;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.HWPCharNormal;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.ParaText;

public class ParaTextSetter {
	public static void changeText(Paragraph p, int startIndex, int endIndex, String text) {
		String text2 = replaceCRLF(text);
		deleteOriginChar(p.getText(), startIndex, endIndex);
		insertChar(p.getText(), startIndex, text2);
		adjustParaCharShape(p.getCharShape(), startIndex, endIndex, text2);
		deleteLineSeg(p);
	}
	
	private static String replaceCRLF(String text) {
		StringBuffer sb = new StringBuffer();
		int len = text.length();
		for (int index = 0; index < len; index++) {
			char ch = text.charAt(index);
			if (ch == '\r') {
				if (text.charAt(index + 1) == '\n') {
					index++;
				}
				sb.append('\r');
			} else if (ch == '\n') { 
				if (text.charAt(index + 1) == '\r') {
					index++;
				}
				sb.append('\r');
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	private static void deleteOriginChar(ParaText paraText, int startIndex, int endIndex) {
		for (int index = startIndex; index <= endIndex; index++) {
			paraText.getCharList().remove(startIndex);
		}
	}

	private static void insertChar(ParaText paraText, int startIndex, String text) {
		int len = text.length();
		for (int index = 0; index < len; index++) {
			HWPCharNormal ch = new HWPCharNormal();
			ch.setCode((short) text.codePointAt(index));
			paraText.getCharList().add(startIndex + index, ch);
		}
	}

	private static void adjustParaCharShape(ParaCharShape paraCharShape, int startIndex, int endIndex, String text) {
		int len = text.length();
		ArrayList<CharPositonShapeIdPair> list = paraCharShape.getPositonShapeIdPairList();
		ArrayList<CharPositonShapeIdPair> deleteItems = new ArrayList<CharPositonShapeIdPair>();
		for (CharPositonShapeIdPair cpsip : list) {
			if (cpsip.getPositon() < startIndex) {
				continue;
			} else if (cpsip.getPositon() >= startIndex && cpsip.getPositon() <= endIndex)  {
				deleteItems.add(cpsip);
			} else if (cpsip.getPositon() < endIndex){
				int oldLen = endIndex - startIndex + 1;
				cpsip.setPositon(cpsip.getPositon() + oldLen - len);
			}
		}
		for (CharPositonShapeIdPair cpsip : deleteItems) {
			list.remove(cpsip);
		}
	}
	
	private static void deleteLineSeg(Paragraph p) {
		p.deleteLineSeg();
	}
}
