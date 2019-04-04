package com.company.consultant.processor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import org.springframework.stereotype.Component;

import com.company.consultant.dto.SettingsDTO;
import com.company.consultant.models.Settings;
import com.company.consultant.util.DtoConverter;

@Component
public class SettingProcessor extends BaseProcessor{

	@Override
	public Object processAndSave(Object obj) {
		
		if(obj instanceof Settings){
			Settings settings = (Settings) obj;
			
			SettingsDTO settingsDTO = new SettingsDTO();

			if(settings.getSettingId() != null){
				settingsDTO.setSettingsId(settings.getSettingId());
				settingsDTO = (SettingsDTO) dao.findByEntity(settingsDTO).get(0);
			} else {
				settingsDTO.setSettingType(settings.getType());
				settingsDTO.setEmployeeId(settings.getEmployeeId());				
			}
			
			StringWriter sw = new StringWriter();
			JAXB.marshal(settings, sw);
			String settingsXml = sw.toString();
			
			settingsDTO.setSettingsDetails(settingsXml);
			
			settingsDTO = (SettingsDTO) dao.save(settingsDTO);

			return settings;
			
		}
		return null;


	}

	public Settings search(Settings obj) {
		SettingsDTO settingsDTO = new SettingsDTO();
		DtoConverter.copyProperties(obj, settingsDTO);
		List<SettingsDTO> settingsDTOs = (List<SettingsDTO>) dao.findByEntity(settingsDTO);
	    if(settingsDTOs != null && settingsDTOs.size() > 0) {
	    	settingsDTO = settingsDTOs.get(0);
	    	StringReader stringReader = new StringReader(settingsDTO.getSettingsDetails());
	    	Settings settings = JAXB.unmarshal(stringReader, Settings.class);
	    	settings.setSettingId(settingsDTO.getSettingsId());
	    	return settings;
	    }
		return null;
	}

	
}
