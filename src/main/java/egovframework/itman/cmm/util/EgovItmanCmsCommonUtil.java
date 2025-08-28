package egovframework.itman.cmm.util;

import org.springframework.ui.Model;

public class EgovItmanCmsCommonUtil {

	public static String alertMove(final Model model, String msg, String moveUrl) {
		model.addAttribute("message", msg);
		model.addAttribute("returnUrl", moveUrl);
		return "itman/_inc/alertMove";
	}
}
