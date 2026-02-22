
public class DisciplinaryRule extends Rule {
	@Override
	public Rule.Result check(StudentProfile s) {
		if (s.disciplinaryFlag != LegacyFlags.NONE) {
			return new Result(false, "disciplinary flag present");
		} else {
			return new Result(true, null);
		}
	}
}
