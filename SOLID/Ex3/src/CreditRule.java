
public class CreditRule extends Rule {
	public Rule.Result check(StudentProfile s) {
		if (s.earnedCredits < RuleInput.minCredits) {
			return new Result(false, "credits below " + RuleInput.minCredits);
		} else {
			return new Result(true, null);
		}
	}
}
