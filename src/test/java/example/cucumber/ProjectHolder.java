package example.cucumber;

import application.Project;

public class ProjectHolder {
    private Project project;

    public Project getProject() {
        if(project == null){
            project = new Project("holde projekt", 1, 1, 1);
        }return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
