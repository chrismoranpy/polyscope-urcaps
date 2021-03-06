package com.ur.urcap.Morans;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.configuration.debugging.ProgramDebuggingSupport;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

import java.util.Locale;

public class MoransProgramNodeService
		implements SwingProgramNodeService<MoransProgramNodeContribution, MoransProgramNodeView> {

	@Override
	public String getId() {
		return "Morans";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setDeprecated(false);
		configuration.setChildrenAllowed(true);
		configuration.setUserInsertable(true);

		ProgramDebuggingSupport programDebuggingSupport = configuration.getProgramDebuggingSupport();
		programDebuggingSupport.setAllowBreakpointOnChildNodesInSubtree(true);
		programDebuggingSupport.setAllowStartFromChildNodesInSubtree(true);
	}

	@Override
	public String getTitle(Locale locale) {
		return "Morans";
	}

	@Override
	public MoransProgramNodeView createView(ViewAPIProvider apiProvider) {
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5 ? new V5Style() : new V3Style();
		return new MoransProgramNodeView(style);
	}

	@Override
	public MoransProgramNodeContribution createNode(ProgramAPIProvider apiProvider, MoransProgramNodeView view,
			DataModel model, CreationContext context) {
		return new MoransProgramNodeContribution(apiProvider, view, model);
	}
}
