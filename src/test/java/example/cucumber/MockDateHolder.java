package example.cucumber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import application.DateServer;
import application.ProjectMenu;

public class MockDateHolder {
	
	DateServer dateServer = mock(DateServer.class);
	
	public MockDateHolder(ProjectMenu projectMenu) {
		GregorianCalendar calendar = new GregorianCalendar();
		setDate(calendar);
		projectMenu.setDateServer(dateServer);
	}

	public void setDate(Calendar calendar) {
		Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
		when(this.dateServer.getDate()).thenReturn(c);
	}
	
	public void advanceDateByDays(int days) {
		Calendar currentDate = dateServer.getDate();
		Calendar nextDate = new GregorianCalendar();
		nextDate.setTime(currentDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, days);
		setDate(nextDate);
	}
}
