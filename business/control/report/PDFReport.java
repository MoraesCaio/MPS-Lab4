package business.control.report;

import business.model.User;

import java.time.LocalDate;

/**
 * Created by caiomoraes on 24/11/17.
 */
public class PDFReport extends ReportTemplate
{
    @Override
    public String outputFormat()
    {
        return  "PDF FILE";
    }

    @Override
    public String info(User user)
    {
        return LocalDate.now() + "\n" + user.getGenealogicalTree().toString();
    }
}
