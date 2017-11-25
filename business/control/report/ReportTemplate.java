package business.control.report;

import business.model.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by caiomoraes on 24/11/17.
 */
public abstract class ReportTemplate
{
    public String reportFile = "report.txt";

    public void report(User user)
    {
        List<String> lines = Arrays.asList(outputFormat(), user.toString(), info(user));
        Path file = Paths.get(reportFile);
        try
        {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e)
        {
            System.out.println("Couldn't write file.");
        }
    }

    public abstract String outputFormat();

    public abstract String info(User user);
}