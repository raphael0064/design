package design.concreate.composite;

import java.util.List;

public abstract class TextTagComposite extends TextTag {
	public abstract List<TextTag> getTags();

	public abstract void addTag(TextTag tag);

}
