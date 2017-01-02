package org.geepawhill.contentment.outline;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.geepawhill.contentment.outline.KvMatcher.MatchResult;
import static org.junit.Assert.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KvVisualMatcher
{

	private KvMatcher matcher;

	public KvVisualMatcher()
	{
		matcher = new KvMatcher();
	}

	public void assertEqual(String message, KvOutline expected, KvOutline actual)
	{
		MatchResult result = matcher.match(expected, actual);
		if (result.match == true) return;
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			final Stage dialog = makeDialog();
			ObservableList<KvDifference> observableDifferences = makeObservableDifferences(result);
			TableView<KvDifference> differencesTable = makeDifferencesTable(observableDifferences);
			dialog.setScene(loadDifferencesToScene(differencesTable));
			dialog.setOnCloseRequest((event) -> countDownLatch.countDown());
			dialog.show();
		});
		try
		{
			countDownLatch.await();
		}
		catch (InterruptedException ignored)
		{
		}
		System.out.println(expected.asText("Expected"));
		System.out.println(actual.asText("Actual"));
		fail("Mismatch in outlines: "+message);
	}

	private Scene loadDifferencesToScene(TableView<KvDifference> detailsView)
	{
		AnchorPane pane = new AnchorPane();
		AnchorPane.setBottomAnchor(detailsView, 0d);
		AnchorPane.setTopAnchor(detailsView, 0d);
		AnchorPane.setLeftAnchor(detailsView, 0d);
		AnchorPane.setRightAnchor(detailsView, 0d);
		pane.getChildren().add(detailsView);
		Scene scene = new Scene(pane, 1200d, 800d);
		return scene;
	}

	private TableView<KvDifference> makeDifferencesTable(ObservableList<KvDifference> observable)
	{
		TableView<KvDifference> detailsView = new TableView<>(observable);
		detailsView.setStyle("-fx-font: 18px Tahoma");

		TableColumn<KvDifference, String> keyColumn = new TableColumn<>("Key");
		keyColumn.setPrefWidth(400d);
		keyColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getKey()));
		detailsView.getColumns().add(keyColumn);

		TableColumn<KvDifference, String> expectedColumn = new TableColumn<>("Expected");
		expectedColumn.setPrefWidth(250d);
		expectedColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getExpected()));
		detailsView.getColumns().add(expectedColumn);

		TableColumn<KvDifference, String> actualColumn = new TableColumn<>("Actual");
		actualColumn.setPrefWidth(250d);
		actualColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getActual()));
		detailsView.getColumns().add(actualColumn);
		return detailsView;
	}

	private ObservableList<KvDifference> makeObservableDifferences(MatchResult result)
	{
		List<KvDifference> messages = result.details.asLeafList();
		ObservableList<KvDifference> observable = FXCollections.observableList(messages);
		FXCollections.sort(observable, (left, right) -> left.getKey().compareTo(right.getKey()));
		return observable;
	}

	private Stage makeDialog()
	{
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		return dialog;
	}
}
