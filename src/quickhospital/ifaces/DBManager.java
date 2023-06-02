package quickhospital.ifaces;


import java.sql.Connection;
import java.util.*;

import quickhospital.pojos.*;


public interface DBManager {
	Connection getConnection();
	void disconnect();
}

