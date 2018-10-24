package kr.dogfoot.hwplib.object.bodytext.control.equation;

import kr.dogfoot.hwplib.object.etc.Color4Byte;

/**
 * 수식 컨트롤의 수식 정보를 나타내는 레코드
 * 
 * @author neolord
 */
public class EQEdit {
	/**
	 * 속성(스크립트가 차지하는 범위. 첫 비트가 켜져 있으면 줄 단위, 꺼져 있으면 글자 단위??)
	 */
	private long property;
	/**
	 * 한글 수식 스크립트
	 */
	private String script;
	/**
	 * 수식 글자 크기
	 */
	private long letterSize;
	/**
	 * 글자 색상
	 */
	private Color4Byte letterColor;
	/**
	 * base line
	 */
	private short baseLine;
	/**
	 * 버전 정보 ??
	 */
	private String versionInfo;
	/**
	 * 알수 없는 문자열 ??
	 */
	private String unknown;

	/**
	 * 생성자
	 */
	public EQEdit() {
		letterColor = new Color4Byte();
	}

	/**
	 * 속성값을 반환한다.
	 * 
	 * @return 속성값
	 */
	public long getProperty() {
		return property;
	}

	/**
	 * 속성값을 설정한다.
	 * 
	 * @param property
	 *            속성값
	 */
	public void setProperty(long property) {
		this.property = property;
	}

	/**
	 * 한글 수식 스크립트를 반환한다.
	 * 
	 * @return 한글 수식 스크립트
	 */
	public String getScript() {
		return script;
	}

	/**
	 * 한글 수식 스크립트를 설정한다.
	 * 
	 * @param script
	 *            한글 수식 스크립트
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * 수식 글자 크기를 반환한다.
	 * 
	 * @return 수식 글자 크기
	 */
	public long getLetterSize() {
		return letterSize;
	}

	/**
	 * 수식 글자 크기를 설정한다.
	 * 
	 * @param letterSize
	 *            수식 글자 크기
	 */
	public void setLetterSize(long letterSize) {
		this.letterSize = letterSize;
	}

	/**
	 * 글자 색상 객체를 반환한다.
	 * 
	 * @return 글자 색상 객체
	 */
	public Color4Byte getLetterColor() {
		return letterColor;
	}

	/**
	 * base line을 반환한다.
	 * 
	 * @return base line
	 */
	public short getBaseLine() {
		return baseLine;
	}

	/**
	 * base line을 설정한다.
	 * 
	 * @param baseLine
	 *            base line
	 */
	public void setBaseLine(short baseLine) {
		this.baseLine = baseLine;
	}

	/**
	 * 버전 정보를 반환한다.
	 * 
	 * @return 버전 정보
	 */
	public String getVersionInfo() {
		return versionInfo;
	}

	/**
	 * 버전 정보를 설정한다.
	 * 
	 * @param versionInfo
	 *            버전 정보
	 */
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	/**
	 * 알 수 없는 문자열를 반환한다. 
	 * @return 알 수 없는 문자열 
	 */
	public String getUnknown() {
		return unknown;
	}

	/**
	 * 알 수 없는 문자열를 설정한다.
	 * 
	 * @param unknown 알 수 없는 문자열 
	 */
	public void setUnknown(String unknown) {
		this.unknown = unknown;
	}
}
