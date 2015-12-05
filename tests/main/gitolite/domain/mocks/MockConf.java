package main.gitolite.domain.mocks;

public class MockConf {

	public final String file = "# sample conf/gitolite.conf file\n"
			+ "\n"
			+ "@staff              =   dilbert alice           # groups\n"
			+ "@projects           =   foo bar\n"
			+ "\n"
			+ "\n"
			+ "repo @projects baz                              # repos\n"
			+ "RW+             =   @staff                  # rules\n"
			+ "-       master  =   ashok\n"
			+ "RW              =   ashok\n"
			+ "R               =   wally\n"
			+ "\n"
			+ "option deny-rules           =   1           # options\n"
			+ "config hooks.emailprefix    = '[%GL_REPO] ' # git-config\n";

}
