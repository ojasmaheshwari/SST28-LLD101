package com.example.reports;

import java.util.HashMap;
import java.util.Map;

public class ReportProxy implements Report {

	private final String reportId;
	private final String title;
	private final String classification;
	private final AccessControl accessControl = new AccessControl();

	private static Map<String, RealReport> cache = new HashMap<>();

	public ReportProxy(String reportId, String title, String classification) {
		this.reportId = reportId;
		this.title = title;
		this.classification = classification;
	}

	@Override
	public void display(User user) {
		String cacheKey = reportId + "|" + title + "|" + classification;

		if (cache.containsKey(cacheKey)) {
			cache.get(cacheKey).display(user);
		} else {
			if (accessControl.canAccess(user, classification)) {
				RealReport realReport = new RealReport(reportId, title, classification);
				cache.put(cacheKey, realReport);
				realReport.display(user);
			} else {
				throw new RuntimeException("User " + user.getName() + " with role " + user.getRole()
						+ " does not have priveleges to access this report. Report Classification: " + classification);
			}
		}
	}
}
