package client;

public class PlayGameController {
    PlayGameView playGameView;
    Client client;

    PlayGameController(PlayGameView playGameView, Client client) {
        this.playGameView = playGameView;
        this.client = client;
        upgradeAction();
        moveAction();
        attackAction();
        techAction();
        doneAction();
        confirmAction();
    }

    public void upgradeAction() {
        playGameView.upgrade.setOnAction(e -> {
            client.sendInstruction(playGameView.upgrade.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
        });
    }

    public void moveAction() {
        playGameView.move.setOnAction(e -> {
            client.sendInstruction(playGameView.move.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
        });
    }

    public void attackAction() {
        playGameView.attack.setOnAction(e -> {
            client.sendInstruction(playGameView.attack.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
        });
    }

    public void techAction() {
        playGameView.tech.setOnAction(e -> {
            client.sendInstruction(playGameView.tech.getText().substring(0, 1));
            String prompt = client.recvInstruction();
        });
    }

    public void doneAction() {
        playGameView.done.setOnAction(e -> {
            client.sendInstruction(playGameView.done.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            String prompt1 = client.recvAssignPrompt();
            String prompt2 = client.recvInstruction();
            playGameView.prompt1.setText(prompt1);
            playGameView.prompt2.setText(prompt2);
        });
    }

    public void confirmAction() {
        playGameView.confirm.setOnAction(e -> {
            client.sendInstruction(playGameView.input.getText());
            String prompt2 = client.recvInstruction();
            playGameView.prompt2.setText(prompt2);
            playGameView.input.setVisible(false);
            playGameView.confirm.setVisible(false);
        });
    }
}
