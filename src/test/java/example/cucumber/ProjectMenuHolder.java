package example.cucumber;

import application.ProjectMenu;

public class ProjectMenuHolder {
    private ProjectMenu projectMenu;

    public ProjectMenu getProjectMenu() {
        if(projectMenu == null){
            projectMenu = new ProjectMenu("HolderPoul");
        }return projectMenu;
    }
    public void setProjectMenu(ProjectMenu projectMenu) {
        this.projectMenu = projectMenu;
    }

}
