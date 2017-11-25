package business.control.report;

import business.model.User;

/**
 * Created by caiomoraes on 25/11/17.
 */
public class ReportWriter
{
    private ReportStrategy reportStrategy;

    public ReportWriter(ReportStrategy reportStrategy)
    {
        this.reportStrategy = reportStrategy;
    }

    public void setReportStrategy(ReportStrategy reportStrategy)
    {
        this.reportStrategy = reportStrategy;
    }

    public void writeReport(User user)
    {
        reportStrategy.report(user);
    }
}
