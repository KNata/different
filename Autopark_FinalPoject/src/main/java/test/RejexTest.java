package test;

import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Driver;
import Model.Visitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RejexTest {


    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void isMatch() throws SQLException {
        String driverID = "TR337777";
        Pattern pattern = Pattern.compile("[A-Z]{2}\\d{6}");
        Matcher matcher = pattern.matcher(driverID);
        boolean isMatch = matcher.find();
        assertTrue(isMatch);
    }

}