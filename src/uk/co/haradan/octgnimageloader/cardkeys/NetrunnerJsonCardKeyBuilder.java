package uk.co.haradan.octgnimageloader.cardkeys;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class NetrunnerJsonCardKeyBuilder implements JsonCardKeyBuilder<NetrunnerCardKey> {
	
	private int cycleNum;
	private int cardNum;

	@Override
	public void startCard() {
		cycleNum = -1;
		cardNum = -1;
	}

	@Override
	public void readField(String fieldName, JsonToken valueToken, JsonParser parser) throws JsonParseException, IOException {
		if(valueToken != JsonToken.VALUE_NUMBER_INT) return;
		
		if("number".equals(fieldName)) {
			cardNum = parser.getIntValue();
		} else if("cyclenumber".equals(fieldName)) {
			cycleNum = parser.getIntValue();
		}
	}

	@Override
	public void endCard() {}

	@Override
	public NetrunnerCardKey buildKey() {
		if(cycleNum < 0 || cardNum < 0) return null;
		
		return new NetrunnerCardKey(cycleNum, cardNum);
	}
	
}
