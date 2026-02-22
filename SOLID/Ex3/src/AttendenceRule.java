
public class AttendenceRule extends Rule {

	@Override
	public Rule.Result check(StudentProfile s) {
		if (s.attendancePct < RuleInput.minAttendance) {
			return new Result(false, "attendence below " + RuleInput.minAttendance);
		} else {
			return new Result(true, null);
		}
	}
}
