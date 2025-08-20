package egovframework.itman.auth.service.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import egovframework.itman.auth.service.EgovItmanAuthProcService;
import egovframework.itman.auth.service.EgovitmanEmailCodeVO;
import egovframework.itman.member.service.EgovItmanMemberVO;


@Service("egovItmanLoginProcService")
public class EgovItmanAuthProcServiceImpl implements EgovItmanAuthProcService{
	@Autowired
    private EgovItmanAuthProcServiceDAO egovItmanLoginProcServiceDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public boolean isEmailExists(String memMail) {
		int count = egovItmanLoginProcServiceDAO.isEmailExists(memMail);
		return count > 0;
	}

	@Override
	public EgovitmanEmailCodeVO sendEmailCode(EgovItmanMemberVO vo, String mode) {
		
		// 인증 번호 생성
		String num = String.format("%04d", new Random().nextInt(10_000));
		
		// 이메일 코드 생성을 위한 vo 작성
		EgovitmanEmailCodeVO cvo = new EgovitmanEmailCodeVO();
		cvo.setMemName(vo.getMemName());
		cvo.setEmail(vo.getMemMail());
		cvo.setEcNum(num);
		cvo.setEcMode(mode);
		cvo.setRegDate(LocalDateTime.now().toString());
		
		// 이메일 코드 확인을 위해 insert
		egovItmanLoginProcServiceDAO.insertEmailCode(cvo);
		
		// 이메일 코드 발송
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(vo.getMemMail()); // 송신인 이메일 주소 설정
			helper.setSubject("ITMan 아이티맨 " + mode + " 인증번호."); // 제목
			helper.setText(mode + " 인증번호 : " + "'" + num + "'"); // 내용
			helper.setFrom(new InternetAddress("sunwoo4916000@naver.com", "ITMan 아이티맨")); // 발신인 정보 기입
			
			mailSender.send(message); // 발송
		} catch(MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			
		return cvo;
	}
	
	@Override
    public int insertUser(EgovItmanMemberVO vo) {
        return egovItmanLoginProcServiceDAO.insertUser(vo);
    }

	@Override
	public EgovItmanMemberVO loginProc(String memMail, String memPw) {
		EgovItmanMemberVO vo = egovItmanLoginProcServiceDAO.selectUser(memMail);
		if(vo != null && new BCryptPasswordEncoder().matches(memPw, vo.getMemPw())) {
			return vo;
		}
		return null;
	}

	@Override
	public EgovItmanMemberVO findEmail(String memName, String memTel) {
		return egovItmanLoginProcServiceDAO.findEmail(memName, memTel);
	}

	@Override
	public EgovItmanMemberVO findMemIdx(String memName, String memMail, String memTel) {
		return egovItmanLoginProcServiceDAO.findMemIdx(memName, memMail, memTel);
	}

	@Override
	public EgovItmanMemberVO selectUserByIdx(String memMail) {
		return egovItmanLoginProcServiceDAO.selectUserByIdx(memMail);
	}

	@Override
	public int updateUserPw(String memIdx, String memPw) {
		return egovItmanLoginProcServiceDAO.updateUserPw(memIdx, memPw);
	}

	@Override
	public EgovItmanMemberVO mypageSelectUser(String memIdx) {
		return egovItmanLoginProcServiceDAO.mypageSelectUser(memIdx);
	}


}
