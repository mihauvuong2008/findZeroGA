import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ga_training.Trainer;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;

public class FindZeroMainWindow {

	protected Shell shlGaTrainer;
	Trainer trainner;
	private Label lblValue_2;
	private Label lblValue;
	private Label lblValue_1;
	private Label label;
	private Label label_1;
	private Label label_Varat;
	private Label label_Varat_1;
	private Label label_Varat_1_1;
	private Label lblValue_3;
	private Label label_Varat_1_1_1;
	private Label lblValue_3_1;
	private ExcThread trainThread;
	private Scale scale_4;
	private Scale scale_1_1;
	private Scale scale_1_1_1;
	private Scale scale_1_1_1_1;
	private Composite composite_3;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindZeroMainWindow window = new FindZeroMainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlGaTrainer.open();
		shlGaTrainer.layout();
		while (!shlGaTrainer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGaTrainer = new Shell();
		shlGaTrainer.setImage(SWTResourceManager.getImage(FindZeroMainWindow.class, "/newt/data/jogamp-32x32.png"));
		shlGaTrainer.setSize(704, 517);
		shlGaTrainer.setText("GA trainer");
		shlGaTrainer.setLayout(new GridLayout(4, false));
		trainner = new Trainer();

		Composite composite = new Composite(shlGaTrainer, SWT.NONE);
		composite.setLayout(new GridLayout(8, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));

		Label lblFirstclasssize = new Label(composite, SWT.NONE);
		lblFirstclasssize.setText("firstClass\r\nsize:");

		final Scale scale_3 = new Scale(composite, SWT.NONE);
		scale_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		scale_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int data = scale_3.getSelection();
				trainner.setFirstClasssize(data);
				lblValue_2.setText(String.valueOf(data));
			}
		});
		scale_3.setMaximum(50000);
		scale_3.setSelection(trainner.getFirstClasssize());

		lblValue_2 = new Label(composite, SWT.NONE);
		lblValue_2.setText("" + trainner.getFirstClasssize());

		Label lblsepar = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_lblsepar = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 4);
		gd_lblsepar.widthHint = 25;
		lblsepar.setLayoutData(gd_lblsepar);
		lblsepar.setText("asd");

		Label lblLoop = new Label(composite, SWT.NONE);
		lblLoop.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblLoop.setText("Loop:");

		final Scale scale = new Scale(composite, SWT.NONE);
		scale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		scale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int data = scale.getSelection();
				trainner.setLoop(data);
				lblValue.setText(String.valueOf(data));
			}
		});
		scale.setMaximum(10000);
		scale.setSelection(trainner.getLoop());

		lblValue = new Label(composite, SWT.NONE);
		lblValue.setText("" + trainner.getLoop());

		Label lblMinPopSize = new Label(composite, SWT.NONE);
		lblMinPopSize.setText("minimum \r\npop size:");

		final Scale scale_3_2 = new Scale(composite, SWT.NONE);
		scale_3_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		scale_3_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trainner.setMinimumPopsize(scale_3_2.getSelection());
				lblValue_3.setText(String.valueOf(scale_3_2.getSelection()));
			}
		});
		scale_3_2.setMaximum(500000);
		scale_3_2.setMinimum(500);
		scale_3_2.setSelection(trainner.getMinimumPopsize());

		lblValue_3 = new Label(composite, SWT.NONE);
		lblValue_3.setText("" + trainner.getMinimumPopsize());

		Label lblNumofchild = new Label(composite, SWT.NONE);
		lblNumofchild.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNumofchild.setText("numOf\r\nChild: ");

		final Scale scale_2 = new Scale(composite, SWT.NONE);
		scale_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		scale_2.setMaximum(5);
		scale_2.setMinimum(1);
		scale_2.setSelection(trainner.getNumOfChild());
		scale_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int data = scale_2.getSelection();
				trainner.setNumOfChild(scale_2.getSelection());
				label.setText(String.valueOf(data));
			}
		});

		label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label.setText("" + trainner.getNumOfChild());

		Label lblMaxPopSize_1 = new Label(composite, SWT.NONE);
		lblMaxPopSize_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		lblMaxPopSize_1.setText("maximum \r\npop size:");

		final Scale scale_3_2_1 = new Scale(composite, SWT.NONE);
		scale_3_2_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int maximunPopsize = scale_3_2_1.getSelection();
				trainner.setMaximunPopsize(maximunPopsize);
				lblValue_3_1.setText("" + maximunPopsize);
			}
		});
		scale_3_2_1.setIncrement(10);
		scale_3_2_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));
		scale_3_2_1.setMaximum(80000);
		scale_3_2_1.setMinimum(35000);
		scale_3_2_1.setSelection(40000);

		lblValue_3_1 = new Label(composite, SWT.NONE);
		lblValue_3_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));
		lblValue_3_1.setText("40000");

		Label lblPlannchild = new Label(composite, SWT.NONE);
		lblPlannchild.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblPlannchild.setText("planNmOfChl: ");

		final Spinner spinner = new Spinner(composite, SWT.BORDER);
		spinner.setMaximum(8);
		spinner.setMinimum(2);
		spinner.setSelection(5);
		spinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trainner.setNChildPlan(spinner.getSelection() / 10);
			}
		});
		GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinner.widthHint = 45;
		spinner.setLayoutData(gd_spinner);
		new Label(composite, SWT.NONE);

		final Button btnNatureSelection = new Button(composite, SWT.CHECK);
		btnNatureSelection.setSelection(true);
		btnNatureSelection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trainner.setNaturalFitnessScores(btnNatureSelection.getSelection());
				System.out.println("btnNatureSelection.getSelection(): " + btnNatureSelection.getSelection());
			}
		});

		GridData gd_btnNatureSelection = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_btnNatureSelection.heightHint = 25;
		btnNatureSelection.setLayoutData(gd_btnNatureSelection);
		btnNatureSelection.setText("Nature selection");

		Label lblNaturalfitnessscores = new Label(shlGaTrainer, SWT.NONE);
		lblNaturalfitnessscores.setText("selectionSize \r\nratio:");

		final Scale scale_1 = new Scale(shlGaTrainer, SWT.NONE);
		scale_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double data = (double) ((double) scale_1.getSelection() / 1000);
				trainner.setSelectionRatio(data);
				lblValue_1.setText(String.valueOf(data));
			}
		});
		scale_1.setMaximum(1000);
		scale_1.setMinimum(1);
		scale_1.setSelection((int) (trainner.getSelectionRatio() * 1000));
		scale_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		lblValue_1 = new Label(shlGaTrainer, SWT.NONE);
		GridData gd_lblValue_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblValue_1.widthHint = 40;
		lblValue_1.setLayoutData(gd_lblValue_1);
		lblValue_1.setText("" + trainner.getSelectionRatio());

		composite_3 = new Composite(shlGaTrainer, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		composite_3.setLayout(new GridLayout(3, false));

		Label lblControl = new Label(composite_3, SWT.NONE);
		lblControl.setText("control: ");

		final Scale scale_5 = new Scale(composite_3, SWT.NONE);
		scale_5.setMaximum(10000);
		scale_5.setMinimum(100);
		scale_5.setSelection(500);

		final Label lblValue_1_1 = new Label(composite_3, SWT.NONE);
		GridData gd_lblValue_1_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblValue_1_1.widthHint = 50;
		lblValue_1_1.setLayoutData(gd_lblValue_1_1);
		lblValue_1_1.setText("" + scale_5.getSelection());
		scale_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int perf = scale_5.getSelection();
				double f1 = scale_1_1.getSelection() / scale_1_1.getMaximum();
				double f2 = scale_1_1_1.getSelection() / scale_1_1_1.getMaximum();
				double f3 = scale_1_1_1_1.getSelection() / scale_1_1_1_1.getMaximum();
				scale_1_1.setMaximum(perf);
				scale_1_1_1.setMaximum(perf);
				scale_1_1_1_1.setMaximum(perf);
				scale_1_1.setSelection((int) (f1 * perf));
				scale_1_1_1.setSelection((int) (f2 * perf));
				scale_1_1_1_1.setSelection((int) (f3 * perf));
				lblValue_1_1.setText("" + perf);
			}
		});
		new Label(shlGaTrainer, SWT.NONE);
		new Label(shlGaTrainer, SWT.NONE);

		Composite composite_2 = new Composite(shlGaTrainer, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		composite_2.setLayout(new GridLayout(7, false));

		Label lblMutantratio = new Label(composite_2, SWT.NONE);
		lblMutantratio.setText("MutantRatio: ");

		scale_4 = new Scale(composite_2, SWT.NONE);
		scale_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		scale_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double data = (double) scale_4.getSelection() / 1000;
				trainner.setMutantRatio(data);
				System.out.println(trainner.getMutantRatio() + " " + scale_4.getSelection());
				label_1.setText("" + trainner.getMutantRatio());
			}
		});
		scale_4.setMaximum(1000);
		scale_4.setSelection((int) (trainner.getMutantRatio() * 1000));

		label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("" + String.valueOf(trainner.getMutantRatio()));

		Label lblSep = new Label(composite_2, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_lblSep = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2);
		gd_lblSep.widthHint = 25;
		lblSep.setLayoutData(gd_lblSep);
		lblSep.setText("sep");

		Label lblHybridratio = new Label(composite_2, SWT.NONE);
		lblHybridratio.setText("HybridRatio: ");

		scale_1_1_1 = new Scale(composite_2, SWT.NONE);
		scale_1_1_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		scale_1_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double data = (double) scale_1_1_1.getSelection() / scale_1_1.getMaximum();
				trainner.setHybridRatio(data);
				label_Varat_1_1.setText(String.valueOf(data));
			}
		});
		scale_1_1_1.setMaximum(500);
		scale_1_1_1.setSelection((int) (500 * trainner.getHybridRatio()));

		label_Varat_1_1 = new Label(composite_2, SWT.NONE);
		label_Varat_1_1.setText("" + trainner.getHybridRatio());

		Label lblSomamutantratio = new Label(composite_2, SWT.NONE);
		lblSomamutantratio.setText("SomaMutant\r\nRatio: ");

		scale_1_1 = new Scale(composite_2, SWT.NONE);
		scale_1_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		scale_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double data = (double) scale_1_1.getSelection() / scale_1_1.getMaximum();
				trainner.setSomaMutantRatio(data);
				label_Varat_1.setText(String.valueOf(data));
			}
		});
		scale_1_1.setMaximum(500);
		scale_1_1.setSelection((int) (500 * trainner.getSomaMutantRatio()));

		label_Varat_1 = new Label(composite_2, SWT.NONE);
		label_Varat_1.setText("" + trainner.getSomaMutantRatio());

		Label lblDefendratio = new Label(composite_2, SWT.NONE);
		lblDefendratio.setText("DefendRatio: ");

		scale_1_1_1_1 = new Scale(composite_2, SWT.NONE);
		scale_1_1_1_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		scale_1_1_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double data = (double) scale_1_1_1_1.getSelection() / scale_1_1.getMaximum();
				trainner.setDefendRatio(data);
				label_Varat_1_1_1.setText(String.valueOf(data));
			}
		});
		scale_1_1_1_1.setMaximum(500);
		scale_1_1_1_1.setSelection((int) (500 * trainner.getDefendRatio()));

		label_Varat_1_1_1 = new Label(composite_2, SWT.NONE);
		label_Varat_1_1_1.setText("" + trainner.getDefendRatio());

		Label lblValuerRator = new Label(shlGaTrainer, SWT.NONE);
		lblValuerRator.setText("Valuer rator:");

		final Scale scale_3_1 = new Scale(shlGaTrainer, SWT.NONE);
		scale_3_1.setIncrement(3);
		scale_3_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				double data = (double) scale_3_1.getSelection() / 100000;
				trainner.setValueLevel(data);
				label_Varat.setText(String.valueOf(data));
			}
		});
		scale_3_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		scale_3_1.setMaximum(100000);
		scale_3_1.setMinimum(1);
		scale_3_1.setSelection((int) (100000 * trainner.getValueLevel()));

		label_Varat = new Label(shlGaTrainer, SWT.NONE);
		label_Varat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label_Varat.setText("" + trainner.getValueLevel());
		new Label(shlGaTrainer, SWT.NONE);
		new Label(shlGaTrainer, SWT.NONE);
		new Label(shlGaTrainer, SWT.NONE);
		new Label(shlGaTrainer, SWT.NONE);

		Composite composite_1 = new Composite(shlGaTrainer, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));

		Button btnStartTrainning = new Button(composite_1, SWT.NONE);
		btnStartTrainning.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (trainThread == null) {
					trainThread = new ExcThread();
					trainThread.start();
				} else {
					if (!trainThread.isAlive()) {
						trainThread = new ExcThread();
						trainThread.start();
					}
				}
			}
		});
		btnStartTrainning.setText("Start Trainning");

		final Button btnPauseTrain = new Button(composite_1, SWT.NONE);
		GridData gd_btnPauseTrain = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPauseTrain.widthHint = 85;
		btnPauseTrain.setLayoutData(gd_btnPauseTrain);
		btnPauseTrain.addSelectionListener(new SelectionAdapter() {
			boolean isPause = false;

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isPause) {
					isPause = true;
					trainner.setPauseTrain(isPause);
					btnPauseTrain.setText("Continue Train");
					return;
				}
				isPause = false;
				trainner.setPauseTrain(isPause);
				btnPauseTrain.setText("Pause Train");
			}
		});
		btnPauseTrain.setText("Pause Train");

		Button btnTest = new Button(composite_1, SWT.NONE);
		GridData gd_btnTest = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnTest.widthHint = 85;
		btnTest.setLayoutData(gd_btnTest);
		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}

		});
		btnTest.setText("Test");

		Button btnExit = new Button(composite_1, SWT.NONE);
		GridData gd_btnExit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnExit.widthHint = 85;
		btnExit.setLayoutData(gd_btnExit);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setText("Exit");

	}

	class ExcThread extends Thread {
		public void run() {
			try {
				trainner.Train();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Trainner thread exce " + e.getMessage());
			}
		}
	}
}
