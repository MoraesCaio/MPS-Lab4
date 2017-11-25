package business.control.report;

import business.model.User;

/**
 * Created by caiomoraes on 24/11/17.
 */
public class XMLReport extends ReportTemplate
{
    @Override
    public String outputFormat()
    {
        return "XML FILE";
    }

    @Override
    public String info(User user)
    {
        return user.getGenealogicalTree().toString();
    }
}
