package service;
import java.util.ArrayList;

import domain.*;

public interface IShaduleService {
	
	abstract ArrayList<Shadule> getShadules(String id_major, String scope) throws Exception;
	
	abstract boolean addShadule(Shadule shadule);
	abstract boolean delShadule(int index);
}
