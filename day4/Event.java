import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {
	private final LocalDateTime localDateTime;
	private final String eventDescription;
	private final Integer guard;
	
	public Event(String eventDefinition) {
		Pattern compile = Pattern.compile("\\[(.*)\\](.*)");
		Matcher matcher = compile.matcher(eventDefinition);
		matcher.find();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.localDateTime = LocalDateTime.parse(matcher.group(1), formatter);
		this.eventDescription = matcher.group(2).trim();
		
		Pattern compile1 = Pattern.compile("#([0-9]*)");
		Matcher matcher1 = compile1.matcher(eventDefinition);
		if(matcher1.find()) {
			guard = Integer.parseInt(matcher1.group(1));
		} else {
			guard = null;
		};
		
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}
	
	
	
	@Override
	public String toString() {
		return "Event{" +
				"localDateTime=" + localDateTime +
				", eventDescription='" + eventDescription + '\'' +
				", guard=" + guard +
				'}';
	}
	
	public static void main(String[] args) {
		System.out.println(new Event("[1518-11-01 00:00] Guard #10 begins shift"));
	}
	
	public Integer getGuard() {
		return guard;
	}
}
