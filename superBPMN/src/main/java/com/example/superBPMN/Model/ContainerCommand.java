package com.example.superBPMN.Model;

/**
 * Créé par Ariel NATAF, le 27/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public class ContainerCommand {

	private String Cmd ;
	private String Name ;
	private String HostName ;
	private String Env ;
	private String PortBindings ;
	private String withBinds ;

	public String getCmd() {
		return Cmd;
	}

	public void setCmd(String cmd) {
		Cmd = cmd;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public String getEnv() {
		return Env;
	}

	public void setEnv(String env) {
		Env = env;
	}

	public String getPortBindings() {
		return PortBindings;
	}

	public void setPortBindings(String portBindings) {
		PortBindings = portBindings;
	}

	public String getWithBinds() {
		return withBinds;
	}

	public void setWithBinds(String withBinds) {
		this.withBinds = withBinds;
	}
}
