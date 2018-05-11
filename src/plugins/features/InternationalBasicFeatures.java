package plugins.features;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.cmu.side.util.AbstractTokenizingTool;
import edu.cmu.side.util.GermanTokenizingTool;
import edu.cmu.side.util.TokenizingToolLanguage;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasOffset;
import edu.stanford.nlp.ling.HasTag;
import edu.stanford.nlp.ling.HasWord;

/**
 * This class handles all of the most common features that we see people asking for, including features based around n-grams and parts of speech.
 * It is fairly rare, actually, that anyone needs an extractor other than this one.
 * 
 * Note that this is also some of the more complex code that we have, simply because of how many different things this class is aggregating.
 * Each of the selections in the UI are attached to both a tag and a checkbox, as well as a map of selections for which ones to use at any given time.
 * 
 * New instances of BasicFeatures are set to binary unigrams, and can be modified from there.
 * 
 * @author lightsidelabs
 *
 */
public class InternationalBasicFeatures extends BasicFeatures
{
	protected TokenizingToolLanguage language;
	protected Container wrapper;
	
	public InternationalBasicFeatures()
	{	
		super();
		wrapper = new JPanel(new BorderLayout());
		
		final JComboBox<TokenizingToolLanguage> languageCombo = new JComboBox<TokenizingToolLanguage>(TokenizingToolLanguage.values());
		wrapper.add(languageCombo, BorderLayout.NORTH);
		wrapper.add(panel, BorderLayout.CENTER);

		language = TokenizingToolLanguage.CHINESE;
		languageCombo.setSelectedItem(language);
		
		languageCombo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				language = (TokenizingToolLanguage) languageCombo.getSelectedItem();
			}
		});

	}

	@Override
	protected Component getConfigurationUIForSubclass()
	{
		return wrapper;
	}
	
	protected  <T  extends HasWord & HasTag & HasOffset> List<T> tokenize(String text, boolean includePOS)
	{
		List<CoreLabel> tokens;
		tokens = language.getTool().tokenizeInvertible(text);
		
		if (includePOS)
		{
			tokens = language.getTool().tagInvertible(tokens);
		}
		
		return (List<T>) tokens;
		
	}

	@Override
	public Map<String, String> generateConfigurationSettings()
	{
		Map<String, String> settings = super.generateConfigurationSettings();
		settings.put("tagger_language", language.name());
		return settings;
	}

	@Override
	public void configureFromSettings(Map<String, String> settings)
	{
		super.configureFromSettings(settings);
		language = TokenizingToolLanguage.valueOf(settings.get("tagger_language"));
		this.loadStopWords(language.getTool().punctuationFilename(), language.getTool().stopwordsFilename());
	}
	
	public String getOutputName()
	{
		return "taghelper";
	}
	
	public String toString()
	{
		return "Multilingual Basic Features";
	}

}