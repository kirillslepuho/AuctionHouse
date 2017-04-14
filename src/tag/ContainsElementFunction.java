package tag;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

public class ContainsElementFunction extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public static boolean contains(List list, Object o) {
        return list.contains(o);
    }
}
