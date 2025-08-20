package egovframework.itman.auth.service;

import egovframework.itman.member.service.EgovItmanMemberVO;

public interface EgovItmanAuthProcService {
	
	boolean isEmailExists(String memMail);
	
	EgovitmanEmailCodeVO sendEmailCode(EgovItmanMemberVO vo, String mode);

	int insertUser(EgovItmanMemberVO vo);

	EgovItmanMemberVO loginProc(String memMail, String memPw);

	EgovItmanMemberVO findEmail(String memName, String memTel);

	EgovItmanMemberVO findMemIdx(String memName, String memMail, String memTel);

	EgovItmanMemberVO selectUserByIdx(String memMail);

	int updateUserPw(String memIdx, String memPw);

	EgovItmanMemberVO mypageSelectUser(String memIdx);
}
