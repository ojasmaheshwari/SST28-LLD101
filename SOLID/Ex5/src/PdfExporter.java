import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
	@Override
	public void validate(ExportRequest req) {
		if (req == null) {
			throw new IllegalArgumentException("request cannot be null");
		}
		if (req.title == null) {
			throw new IllegalArgumentException("title cannot be null");
		}
		if (req.body == null) {
			throw new IllegalArgumentException("body cannot be null");
		}
		if (req.body.length() > 20) {
			throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
		}
	}

	@Override
	public ExportResult doExport(ExportRequest req) {
		String fakePdf = "PDF(" + req.title + "):" + req.body;
		return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
	}
}
