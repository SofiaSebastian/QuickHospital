package quickhospital.ifaces;

import java.util.ArrayList;

import quickhospital.pojos.WaitingList;

public interface DBWaitingListsManager {
	ArrayList<WaitingList> readWaitingLists();
	void addWaitingList(WaitingList w);
}
