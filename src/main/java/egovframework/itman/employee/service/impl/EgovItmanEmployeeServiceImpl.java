package egovframework.itman.employee.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.itman.division.service.EgovItmanDivisionVO;
//import egovframework.itman.employee.service.EgovItmanEmployeeDivisionVO;
import egovframework.itman.employee.service.EgovItmanEmployeePositionVO;
import egovframework.itman.employee.service.EgovItmanEmployeeSearchVO;
import egovframework.itman.employee.service.EgovItmanEmployeeService;
import egovframework.itman.employee.service.EgovItmanEmployeeStateVO;
import egovframework.itman.employee.service.EgovItmanEmployeeVO;
import egovframework.itman.group.service.EgovItmanGroupVO;

@Service("egovItmanEmployeeService")

public class EgovItmanEmployeeServiceImpl extends EgovAbstractServiceImpl implements EgovItmanEmployeeService {

	@Resource(name = "egovItmanEmployeeDAO")
	EgovItmanEmployeeDAO egovItmanEmployeeDAO;

	@Override
	public List<?> selectEmploList(EgovItmanEmployeeSearchVO searchVO) throws Exception {
		return egovItmanEmployeeDAO.selectEmploList(searchVO);
	}
	@Override
	public int selectEmploListTotCnt(EgovItmanEmployeeSearchVO searchVO) throws Exception {
		return egovItmanEmployeeDAO.selectEmploListTotCnt(searchVO);
	}

    // group 기능이 정상 동작하기 위해 필요한 메서드입니다.
    @Override
    public int countEmployeeInGroup(EgovItmanGroupVO groupVO) { // throws Exception 삭제

        try {
            // DAO 호출 시 발생할 수 있는 예외를 try 블록으로 감쌉니다.
            return egovItmanEmployeeDAO.countEmployeeInGroup(groupVO);

        } catch (Exception e) {
            // 만약 DAO에서 예외가 발생하면, 여기서 직접 처리합니다.
            // 예를 들어, 에러 로그를 남기고 0을 반환하여 프로그램이 멈추지 않게 할 수 있습니다.
            egovLogger.error("그룹별 직원 수 조회 중 에러 발생", e);
            return 0; // 또는 throw new EgovBizException("에러 메시지"); 와 같이 런타임 예외로 전환
        }
    }
    @Override
    public void insertDivision(EgovItmanDivisionVO divisionVO) throws Exception {
        egovItmanEmployeeDAO.insertDivision(divisionVO);
    }
    @Override
    public void insertPosition(EgovItmanEmployeePositionVO positionVO) throws Exception {
        egovItmanEmployeeDAO.insertPosition(positionVO);
    }
    @Override
    public void insertState(EgovItmanEmployeeStateVO stateVO) throws Exception {
        egovItmanEmployeeDAO.insertState(stateVO);
    }
    @Override
    public List<?> selectDivisionList(String groIdx) throws Exception {
        return egovItmanEmployeeDAO.selectDivisionList(groIdx);
    }

    @Override
    public List<?> selectPositionList(String groIdx) throws Exception {
        return egovItmanEmployeeDAO.selectPositionList(groIdx);
    }

    @Override
    public List<?> selectStateList(String groIdx) throws Exception {
        return egovItmanEmployeeDAO.selectStateList(groIdx);
    }
    @Override
    public void insertEmplo(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.insertEmplo(emploVO);
    }

	@Override
	public List<EgovItmanEmployeeVO> selectEmployeeList(EgovItmanGroupVO egovItmanGroupVO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public EgovItmanEmployeeVO selectEmploView(String empIdx) throws Exception {
        return egovItmanEmployeeDAO.selectEmploView(empIdx);
    }
    
    @Override
    public List<?> selectEmploAssetList(String empIdx) throws Exception {
        return egovItmanEmployeeDAO.selectEmploAssetList(empIdx);
    }
    @Override
    public void updateEmploNum(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploNum", emploVO);
    }
    @Override
    public void updateEmploName(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploName", emploVO);
    }
    @Override
    public void updateEmploMail(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploMail", emploVO);
    }
    @Override
    public void updateEmploTel(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploTel", emploVO);
    }
    @Override
    public void updateEmploDivision(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploDivision", emploVO);
    }
    @Override
    public void updateEmploPos(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploPos", emploVO);
    }
    @Override
    public void updateEmploState(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.updateEmploState", emploVO);
    }
    @Override
    public void deleteEmplo(EgovItmanEmployeeVO emploVO) throws Exception {
        egovItmanEmployeeDAO.update("egovItmanEmployeeDAO.deleteEmplo", emploVO);
    }

	@Override
	public EgovItmanEmployeeVO selectEmploBasicByGroup(Map<String, Object> params) {
		return egovItmanEmployeeDAO.selectEmploBasicByGroup(params);
	}
}

