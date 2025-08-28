package egovframework.itman.auth.service.impl;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.auth.service.EgovitmanEmailCodeVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Repository("egovItmanLoginProcServiceDAO")
public class EgovItmanAuthProcServiceDAO extends EgovAbstractMapper{

	public int isEmailExists(String memMail) {
		return selectOne("isEmailExists", memMail);
		
	}

	public void insertEmailCode(EgovitmanEmailCodeVO cvo) {
		insert("insertEmailCode", cvo);
		
	}
	
	public int insertUser(EgovItmanMemberVO vo) {
		return insert("insertUser", vo);
	}

	public EgovItmanMemberVO selectUser(String memMail) {
		return selectOne("selectUser", memMail);
	}

	public EgovItmanMemberVO findEmail(String memName, String memTel) {
		EgovItmanMemberVO vo = new EgovItmanMemberVO();
		vo.setMemName(memName);
		vo.setMemTel(memTel);
		return selectOne("findEmail", vo);
	}

	public EgovItmanMemberVO findMemIdx(String memName, String memMail, String memTel) {
		EgovItmanMemberVO vo = new EgovItmanMemberVO();
		vo.setMemName(memName);
		vo.setMemMail(memMail);
		vo.setMemTel(memTel);
		return selectOne("findMemIdx", vo);
	}

	public EgovItmanMemberVO selectUserByIdx(String memMail) {
		return selectOne("selectUserByIdx", memMail);
	}

	public int updateUserPw(String memIdx, String memPw) {
		EgovItmanMemberVO vo = new EgovItmanMemberVO();
		vo.setMemIdx(memIdx);
		vo.setMemPw(memPw);
		return update("updateUserPw", vo);
	}
	
	public EgovItmanMemberVO mypageSelectUser(String memIdx) {
		return selectOne("mypageSelectUser", memIdx);
	}


}