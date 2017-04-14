package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class AuctionTypeTag  extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private String type;
    private static final String LOCALE = "local";
    private static final String LOCALE_EN = "localization.local_en";
    private static final String LOCALE_RU = "localization.local_ru";
    private static final String BLITZ = "блиц-аукцион";
    private static final String BLITZ_AUCTION = "local.blitz_auction";
    private static final String ENGLISH_AUCTION= "local.english_auction";
    private static final String EN = "en";


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() throws JspException {
        String message;
        if (type.equalsIgnoreCase(BLITZ)) {
            message = ResourceBundle.getBundle(getBundleName()).getString(BLITZ_AUCTION);
        } else {
            message = ResourceBundle.getBundle(getBundleName()).getString(ENGLISH_AUCTION);
        }
        try {
            pageContext.getOut().write(message);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String getBundleName() {
        if (pageContext.getSession().getAttribute(LOCALE) == null) {
            return LOCALE_EN;
        } else {
            String locale = pageContext.getSession().getAttribute(LOCALE).toString();
            if (locale.equalsIgnoreCase(EN)) {
                return LOCALE_EN;
            } else {
                return LOCALE_RU;
            }
        }
    }
}
