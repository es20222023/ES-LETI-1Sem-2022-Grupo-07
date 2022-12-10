package ES_LETI_1Semestre_2022_GRUPO_07.ES_LETI_1Semestre_2022_GRUPO_07;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event implements Comparable<Event> {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String summary;

	List<Element> elements = new ArrayList<>();

	public Event(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Event(LocalDateTime startDate, LocalDateTime endDate, String summary) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.summary = summary;
	}

	public Event(LocalDateTime startDate, LocalDateTime endDate, String summary, Element element) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.summary = summary;
		elements.add(element);
	}

	public Event(LocalDateTime startDate, LocalDateTime endDate, String summary, List<Element> elements) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.summary = summary;
		this.elements = elements;
		//elements.add(element);
	}



	/**
	 * @return the start date.
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	/**
	 * @param Date the start date to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return the end date.
	 */
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	/**
	 * @param Date the end date to set
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the summary.
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}


	/**
	 * @return the elements list.
	 */
	public List<Element> getElements() {
		return elements;
	}
	

	/**
	 * adds an element to the list of elements.
	 */
	public void addElement(Element element) {
		if(elements.indexOf(element) == -1) {
			this.elements.add(element);
		}
	}
	
	/*
	 * it checks if an Event collides with another one.
	 * Based in 5 possible ways to collide.
	 * Both Event objects start with the same hour, one starts first and ends after the other,
	 * starts first and ends between the start and end date of the other event,
	 * start between the start and end date of the other event and ends after the other end date
	 * and the last possible way, starts and ends between the start and end dates from the other event.
	 */
	public boolean collidesWithEvent(Event other) {
		if(this.getStartDate().getMonth().equals(other.getStartDate().getMonth())) {
			if(this.getStartDate().getDayOfMonth() == other.getStartDate().getDayOfMonth()) {
				
				//começa a mesma hora
				if(other.getStartDate().isEqual(this.getStartDate())) {
					return true;
				}

				//comeca antes acaba depois
				if(other.getStartDate().isBefore(this.getStartDate()) 
					&& other.getEndDate().isAfter(this.getEndDate())) {
					return true;
				}
				//comeca antes acaba dentro
				if(other.getStartDate().isBefore(this.getStartDate()) 
					&& other.getEndDate().isAfter(this.getStartDate()) 
					&& other.getEndDate().isBefore(this.getEndDate())) {
					return true;
				}
				//comeca dentro acaba depois
				if(other.getStartDate().isAfter(this.getStartDate()) 
					&& other.getStartDate().isBefore(this.getEndDate()) 
					&& other.getEndDate().isAfter(this.getEndDate())) {

					return true;
				}
				//comeca dentro acaba dentro
				if(other.getStartDate().isAfter(this.getStartDate()) 
					&& other.getEndDate().isBefore(this.getEndDate())) {
					return true;
				}
			}
		}
		return false;
	}


//	@Override
//	public Event clone() throws CloneNotSupportedException {
//		LocalDateTime clonedStartDate = (LocalDateTime) this.startDate.clone();
//		LocalDateTime clonedEndDate = (LocalDateTime) this.endDate.clone();
//		String clonedSummary = this.summary;
//		List<Element> cloneList = new ArrayList<>();
//		for (Element e: elements) cloneList.add(e.clone());
//		Event cloneEvent = new Event(clonedStartDate, clonedEndDate, clonedSummary, cloneList);
//		return cloneEvent;
//	}
	

	/**
	 * @return a boolean saying if the comparing objects are equal(true) or not(false).
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return endDate.equals(other.endDate) && startDate.equals(other.startDate)
				&& summary.equals(other.summary);
	}
	

	/**
	 * @return a negative integer, zero, or a positive integer as this Event is before date, equal to, or greater than the specified date.
	 */

	@Override
	public int compareTo(Event other) {
		if(other.endDate.compareTo(this.startDate) <= 0) 
			return 1;
		if(other.startDate.compareTo(this.endDate) >= 0) 
			return -1;
		return 0;
	}
	
	/**
	 * @return a string representing the event.
	 */
	@Override
	public String toString() {
		return "Event [Data Ínicio: " + startDate + ", Data fim: " + endDate + ", Evento: " + summary + ",elements: "
				+ elements;
	}



}
