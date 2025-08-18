package egovframework.itman.group.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.asset.service.EgovItmanAssetService;
import egovframework.itman.employee.service.EgovItmanEmployeeService;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Service("egovItmanGroupService")
public class EgovItmanGroupServiceImpl implements EgovItmanGroupService{

	@Resource(name="egovItmanGroupDAO")
	EgovItmanGroupDAO egovItmanGroupDAO;

	@Resource(name="egovItmanAssetService")
	EgovItmanAssetService egovItmanAssetService;
	
	@Resource(name="egovItmanEmployeeService")
	EgovItmanEmployeeService egovItmanEmployeeService;
	
	public List<EgovItmanGroupVO> selectGroupList(EgovItmanMemberVO egovItmanMemberVO) {
		
		return egovItmanGroupDAO.selectGroupList(egovItmanMemberVO);
	}

	@Override
	public int insertGroup(EgovItmanGroupVO egovItmanGroupVO) {
		
		return egovItmanGroupDAO.insertGroup(egovItmanGroupVO);
	}

	@Override
	public EgovItmanGroupVO selectGroup(EgovItmanGroupVO egovItmanGroupVO) {
		return egovItmanGroupDAO.selectGroup(egovItmanGroupVO);
	}
	
	
}
