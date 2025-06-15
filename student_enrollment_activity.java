package com.technosys.StudentEnrollment.StudentInroolmentCount;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.exifinterface.media.ExifInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.technosys.StudentEnrollment.C2778R;
import com.technosys.StudentEnrollment.DataBase.CoronaDataSource;
import com.technosys.StudentEnrollment.RegistrationOTP_Login.Entity.UserProfile;
import com.technosys.StudentEnrollment.StudentInroolmentCount.Entity.SelectListItem;
import com.technosys.StudentEnrollment.StudentInroolmentCount.Entity.StudentClass;
import com.technosys.StudentEnrollment.StudentInroolmentCount.Entity.StudentEnrollment;
import com.technosys.StudentEnrollment.StudentInroolmentCount.SaveingThread.ThreadForOnlyFileSaving;
import com.technosys.StudentEnrollment.StudentInroolmentCount.SaveingThread.ThreadForSavingwithFile;
import com.technosys.StudentEnrollment.Utility.AndroidUtils;
import dk.nodes.filepicker.FilePickerActivity;
import dk.nodes.filepicker.FilePickerConstants;
import dk.nodes.filepicker.uriHelper.FilePickerUriHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.HttpHost;

/* loaded from: classes3.dex */
public class student_enrollment_activity extends AppCompatActivity {
    private static final int ATTACHMENT_CHOICE_BROWSE_DOCUMENT = 5;
    public static final int requestcode = 200;
    TextInputEditText Districtforsch;
    ImageView Im_FileSync;
    ImageView Im_Sync;
    TextInputEditText areatype;
    String[] arrlist;
    TextInputEditText blockortownsch;
    Button btn_final_save;
    Button btn_save_as_draft;
    Button btn_sync;
    Button btn_unsync;
    CheckBox checkbox_accept;
    String fileName;
    long fileSize;
    String filepath;
    ImageView imageforattachment;
    Intent intent;
    ImageView ivforclear;
    TextView level_totalInrollment1;
    TextView level_totalInrollment2;
    TextView level_totalInrollment3;
    TextView level_totalInrollment4;
    TextView level_totalInrollment5;
    TextView level_totalInrollment6;
    TextView level_totalInrollment7;
    TextView level_totalInrollment8;
    EditText level_totalboy1;
    EditText level_totalboy2;
    EditText level_totalboy3;
    EditText level_totalboy4;
    EditText level_totalboy5;
    EditText level_totalboy6;
    EditText level_totalboy7;
    EditText level_totalboy8;
    EditText level_totalgirl1;
    EditText level_totalgirl2;
    EditText level_totalgirl3;
    EditText level_totalgirl4;
    EditText level_totalgirl5;
    EditText level_totalgirl6;
    EditText level_totalgirl7;
    EditText level_totalgirl8;
    LinearLayout ll_Button;
    LinearLayout ll_Sync;
    LinearLayout llforattachment;
    TextView pdfView;
    TextView pdfname;
    String saveasfinal;
    TextInputEditText schname;
    TextInputEditText session;
    Spinner spforschoolcategory;
    String spinnerid;
    String spinnername;
    StudentEnrollment studentEnrollment;
    StudentEnrollment studentEnrollmentforchecksaving;
    TableRow tabrow1;
    TableRow tabrow2;
    TableRow tabrow3;
    TableRow tabrow4;
    TableRow tabrow5;
    TableRow tabrow6;
    TableRow tabrow7;
    TableRow tabrow8;
    TextInputEditText totalinroolment;
    TextView tv_Sync;
    TextView tv_info;
    TextInputEditText udicecode;
    String ServerGenerateFileName = "";
    HashMap<String, String> hashMapforselectschool = new HashMap<>();
    List<StudentClass> lstcall = new ArrayList();
    UserProfile userCredential = new UserProfile();
    private int ATTACHMENT_CHOICE_TASK_TAKE_PHOTO_for10 = 1001;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        FileOutputStream fileOutputStream;
        super.onActivityResult(i, i2, intent);
        int i3 = this.ATTACHMENT_CHOICE_TASK_TAKE_PHOTO_for10;
        if (i == i3 && i2 == -1 && intent != null && i == i3 && i2 == -1 && intent != null) {
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("en")).format(new Date());
            String uriString = FilePickerUriHelper.getUriString(intent);
            for (int i4 = 0; i4 < uriString.split("/").length; i4++) {
                if (uriString.contains(".pdf")) {
                    if (uriString.split("/")[i4].contains(".pdf")) {
                        String str = uriString.split("/")[i4];
                    }
                } else if (uriString.split("/")[i4].contains(".jpg")) {
                    String str2 = uriString.split("/")[i4];
                }
            }
            try {
                byte[] readAllBytes = Files.readAllBytes(Paths.get(uriString, new String[0]));
                File dir = getDir("StudentEnrollmentPDF", 0);
                this.fileName = "PDF" + System.currentTimeMillis() + ".pdf";
                File file = new File(dir, this.fileName);
                this.filepath = file.toString();
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        fileOutputStream = null;
                    }
                    fileOutputStream.write(readAllBytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            String str3 = this.filepath;
            if (str3 != null && str3.contains(".pdf") && this.fileName != null) {
                this.pdfname.setVisibility(0);
                this.ivforclear.setVisibility(0);
                this.pdfname.setText(this.fileName);
            } else {
                Toast.makeText(this, "please Select only PDF file with maximum size 5mb", 0).show();
            }
            this.ivforclear.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    student_enrollment_activity.this.ivforclear.setVisibility(8);
                    student_enrollment_activity.this.pdfname.setVisibility(8);
                    student_enrollment_activity.this.pdfname.setText("");
                }
            });
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("en"));
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            simpleDateFormat.format(new Date());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2778R.layout.activity_student_enrollment_activity);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Student Enrollment");
        findbyid();
        showAlertMessase();
        this.userCredential = UserProfile.createObjectFromJson(getSharedPreferences("UserObject", 0).getString("user_data", ""));
        CoronaDataSource coronaDataSource = new CoronaDataSource(this);
        coronaDataSource.open();
        this.studentEnrollment = coronaDataSource.getStudentEnrollmentDetail();
        new ArrayList().clear();
        List<SelectListItem> list = coronaDataSource.getschoolcategorylistl();
        StudentEnrollment studentEnrollment = this.studentEnrollment;
        if (studentEnrollment != null && studentEnrollment.getIsSync() != null && this.studentEnrollment.getIsSync().equalsIgnoreCase("false") && this.studentEnrollment.getIsFinalized() != null && this.studentEnrollment.getIsFinalized().equalsIgnoreCase("true")) {
            this.ll_Sync.setVisibility(0);
            this.Im_Sync.setVisibility(0);
            this.Im_FileSync.setVisibility(8);
            this.ll_Button.setVisibility(8);
            this.checkbox_accept.setVisibility(8);
        } else {
            StudentEnrollment studentEnrollment2 = this.studentEnrollment;
            if (studentEnrollment2 != null && studentEnrollment2.getIsFileSync() != null && this.studentEnrollment.getIsFileSync().equalsIgnoreCase("false") && this.studentEnrollment.getIsFinalized() != null && this.studentEnrollment.getIsFinalized().equalsIgnoreCase("true")) {
                this.Im_Sync.setVisibility(8);
                this.ll_Button.setVisibility(8);
                this.checkbox_accept.setVisibility(8);
                this.Im_FileSync.setVisibility(0);
                this.tv_Sync.setText("Your Pdf is not synced on server please click here to sync");
                this.ll_Sync.setVisibility(0);
            } else {
                StudentEnrollment studentEnrollment3 = this.studentEnrollment;
                if (studentEnrollment3 != null && studentEnrollment3.getIsFinalized() != null && this.studentEnrollment.getIsFinalized().equalsIgnoreCase("true") && (this.studentEnrollment.getIsSync() == null || this.studentEnrollment.getIsSync().equalsIgnoreCase("true"))) {
                    this.ll_Sync.setVisibility(0);
                    this.Im_Sync.setVisibility(8);
                    this.ll_Button.setVisibility(8);
                    this.Im_FileSync.setVisibility(8);
                    this.tv_Sync.setText("Data is already finalized");
                    this.checkbox_accept.setVisibility(8);
                } else {
                    StudentEnrollment studentEnrollment4 = this.studentEnrollment;
                    if (studentEnrollment4 != null && studentEnrollment4.getIsFinalized() != null && this.studentEnrollment.getIsFinalized().equalsIgnoreCase("true")) {
                        this.ll_Button.setVisibility(8);
                        this.checkbox_accept.setVisibility(8);
                    } else {
                        StudentEnrollment studentEnrollment5 = this.studentEnrollment;
                        if (studentEnrollment5 != null && studentEnrollment5.getIsSync() != null && this.studentEnrollment.getIsSync().equalsIgnoreCase("false")) {
                            this.ll_Sync.setVisibility(0);
                            this.Im_Sync.setVisibility(0);
                            this.Im_FileSync.setVisibility(8);
                        } else {
                            this.ll_Button.setVisibility(0);
                            this.ll_Sync.setVisibility(8);
                        }
                    }
                }
            }
        }
        StudentEnrollment studentEnrollment6 = this.studentEnrollment;
        if (studentEnrollment6 != null && studentEnrollment6.getPhysicalFilePath() != null && !this.studentEnrollment.getPhysicalFilePath().equalsIgnoreCase("")) {
            this.llforattachment.setVisibility(0);
            this.pdfView.setVisibility(0);
            this.pdfname.setVisibility(0);
            this.imageforattachment.setVisibility(8);
            this.tv_info.setVisibility(8);
            this.pdfname.setText(this.studentEnrollment.getFilename());
        } else {
            this.imageforattachment.setVisibility(0);
        }
        this.pdfView.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (student_enrollment_activity.this.studentEnrollment.getPhysicalFilePath() != null) {
                    if (student_enrollment_activity.this.studentEnrollment.getPhysicalFilePath().contains(HttpHost.DEFAULT_SCHEME_NAME) || student_enrollment_activity.this.studentEnrollment.getPhysicalFilePath().contains("https")) {
                        student_enrollment_activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(student_enrollment_activity.this.studentEnrollment.getPhysicalFilePath())));
                    }
                }
            }
        });
        this.Im_FileSync.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AndroidUtils.checkYourMobileDataConnection(student_enrollment_activity.this)) {
                    student_enrollment_activity student_enrollment_activityVar = student_enrollment_activity.this;
                    new ThreadForOnlyFileSaving(student_enrollment_activityVar, student_enrollment_activityVar.studentEnrollment, student_enrollment_activity.this.Im_FileSync, student_enrollment_activity.this.tv_Sync).execute(new Void[0]);
                } else {
                    student_enrollment_activity student_enrollment_activityVar2 = student_enrollment_activity.this;
                    Toast.makeText(student_enrollment_activityVar2, student_enrollment_activityVar2.getResources().getString(C2778R.string.no_internet_connectivity), 0).show();
                }
            }
        });
        this.Im_Sync.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AndroidUtils.checkYourMobileDataConnection(student_enrollment_activity.this)) {
                    student_enrollment_activity student_enrollment_activityVar = student_enrollment_activity.this;
                    new ThreadForSavingwithFile(student_enrollment_activityVar, student_enrollment_activityVar.studentEnrollment, student_enrollment_activity.this.Im_Sync, student_enrollment_activity.this.tv_Sync, student_enrollment_activity.this.Im_FileSync).execute(new Void[0]);
                } else {
                    student_enrollment_activity student_enrollment_activityVar2 = student_enrollment_activity.this;
                    Toast.makeText(student_enrollment_activityVar2, student_enrollment_activityVar2.getResources().getString(C2778R.string.no_internet_connectivity), 0).show();
                }
            }
        });
        if (list != null && list.size() > 0) {
            StudentEnrollment studentEnrollment7 = this.studentEnrollment;
            if (studentEnrollment7 != null && studentEnrollment7.getSchoolCategoryId() != null && !this.studentEnrollment.getSchoolCategoryId().equalsIgnoreCase("")) {
                ArrayList arrayList = new ArrayList();
                this.hashMapforselectschool.clear();
                boolean z = false;
                int i = 0;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    this.hashMapforselectschool.put(list.get(i2).getText(), list.get(i2).getValue());
                    arrayList.add(list.get(i2).getText());
                    if (list.get(i2).getValue().equalsIgnoreCase(this.studentEnrollment.getSchoolCategoryId())) {
                        z = true;
                        i = i2;
                    }
                }
                this.spforschoolcategory.setAdapter((SpinnerAdapter) new ArrayAdapter(this, C2778R.layout.support_simple_spinner_dropdown_item, arrayList));
                if (z) {
                    this.spforschoolcategory.setSelection(i);
                }
            } else {
                this.hashMapforselectschool.clear();
                this.arrlist = new String[list.size()];
                for (int i3 = 0; i3 < list.size(); i3++) {
                    this.hashMapforselectschool.put(list.get(i3).getText(), list.get(i3).getValue());
                    this.arrlist[i3] = list.get(i3).getText();
                }
                this.spforschoolcategory.setAdapter((SpinnerAdapter) new ArrayAdapter(this, C2778R.layout.support_simple_spinner_dropdown_item, this.arrlist));
            }
        } else {
            StudentEnrollment studentEnrollment8 = this.studentEnrollment;
            if (studentEnrollment8 != null && studentEnrollment8.getSchoolCategoryName() != null && !this.studentEnrollment.getSchoolCategoryName().equalsIgnoreCase("")) {
                this.arrlist = new String[]{this.studentEnrollment.getSchoolCategoryName()};
                this.spforschoolcategory.setAdapter((SpinnerAdapter) new ArrayAdapter(this, C2778R.layout.support_simple_spinner_dropdown_item, this.arrlist));
            } else {
                this.arrlist = new String[]{"--select--"};
                this.spforschoolcategory.setAdapter((SpinnerAdapter) new ArrayAdapter(this, C2778R.layout.support_simple_spinner_dropdown_item, this.arrlist));
            }
        }
        coronaDataSource.close();
        this.spforschoolcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i4, long j) {
                student_enrollment_activity student_enrollment_activityVar = student_enrollment_activity.this;
                student_enrollment_activityVar.spinnerid = student_enrollment_activityVar.hashMapforselectschool.get(student_enrollment_activity.this.spforschoolcategory.getSelectedItem());
                student_enrollment_activity student_enrollment_activityVar2 = student_enrollment_activity.this;
                student_enrollment_activityVar2.spinnername = student_enrollment_activityVar2.spforschoolcategory.getSelectedItem().toString();
            }
        });
        StudentEnrollment studentEnrollment9 = this.studentEnrollment;
        if (studentEnrollment9 != null) {
            this.Districtforsch.setText(studentEnrollment9.getDistrictName() == null ? "N/A" : this.studentEnrollment.getDistrictName() + "");
            this.blockortownsch.setText(this.studentEnrollment.getBlockTownName() == null ? "N/A" : this.studentEnrollment.getBlockTownName() + "");
            this.totalinroolment.setText(this.studentEnrollment.getTotalEnrollment() == null ? "0" : this.studentEnrollment.getTotalEnrollment() + "");
            if (this.studentEnrollment.getAreaName() != null && this.studentEnrollment.getAreaName().equalsIgnoreCase("R")) {
                this.areatype.setText(this.studentEnrollment.getAreaName() == null ? "N/A" : "Rural");
            } else if (this.studentEnrollment.getAreaName() != null && this.studentEnrollment.getAreaName().equalsIgnoreCase("U")) {
                this.areatype.setText(this.studentEnrollment.getAreaName() == null ? "N/A" : "Urban");
            } else {
                this.areatype.setText(this.studentEnrollment.getAreaName() == null ? "N/A" : this.studentEnrollment.getAreaName() + "");
            }
            this.session.setText(this.studentEnrollment.getSessionName() == null ? "N/A" : this.studentEnrollment.getSessionName() + "");
            this.schname.setText(this.studentEnrollment.getSchool_CodeName() != null ? this.studentEnrollment.getSchool_CodeName() + "" : "N/A");
            this.udicecode.setText(this.studentEnrollment.getUdiseCode() != null ? this.studentEnrollment.getUdiseCode() + "" : "");
            if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase("1")) {
                if (this.studentEnrollment.getLstStudentClass() != null && this.studentEnrollment.getLstStudentClass().size() > 0) {
                    this.level_totalboy1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment());
                    this.level_totalgirl1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment());
                    this.level_totalInrollment1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment());
                    this.level_totalboy2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment());
                    this.level_totalgirl2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment());
                    this.level_totalInrollment2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment());
                    this.level_totalboy3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment());
                    this.level_totalgirl3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment());
                    this.level_totalInrollment3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment());
                    this.level_totalboy4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalBoysEnrollment());
                    this.level_totalgirl4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalGirlsEnrollment());
                    this.level_totalInrollment4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalEnrollment());
                    this.level_totalboy5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(4).getTotalBoysEnrollment());
                    this.level_totalgirl5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(4).getTotalGirlsEnrollment());
                    this.level_totalInrollment5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalEnrollment() != null ? this.studentEnrollment.getLstStudentClass().get(4).getTotalEnrollment() : "0");
                }
                this.tabrow6.setVisibility(8);
                this.tabrow7.setVisibility(8);
                this.tabrow8.setVisibility(8);
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.tabrow1.setVisibility(8);
                this.tabrow2.setVisibility(8);
                this.tabrow3.setVisibility(8);
                this.tabrow4.setVisibility(8);
                this.tabrow5.setVisibility(8);
                this.level_totalboy6.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment());
                this.level_totalgirl6.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment());
                this.level_totalInrollment6.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment());
                this.level_totalboy7.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment());
                this.level_totalgirl7.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment());
                this.level_totalInrollment7.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment());
                this.level_totalboy8.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment());
                this.level_totalgirl8.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment());
                this.level_totalInrollment8.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment() != null ? this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment() : "0");
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
                this.level_totalboy1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalBoysEnrollment());
                this.level_totalgirl1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalGirlsEnrollment());
                this.level_totalInrollment1.setText(this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(0).getTotalEnrollment());
                this.level_totalboy2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalBoysEnrollment());
                this.level_totalgirl2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalGirlsEnrollment());
                this.level_totalInrollment2.setText(this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(1).getTotalEnrollment());
                this.level_totalboy3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalBoysEnrollment());
                this.level_totalgirl3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalGirlsEnrollment());
                this.level_totalInrollment3.setText(this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(2).getTotalEnrollment());
                this.level_totalboy4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalBoysEnrollment());
                this.level_totalgirl4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalGirlsEnrollment());
                this.level_totalInrollment4.setText(this.studentEnrollment.getLstStudentClass().get(3).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(3).getTotalEnrollment());
                this.level_totalboy5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(4).getTotalBoysEnrollment());
                this.level_totalgirl5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(4).getTotalGirlsEnrollment());
                this.level_totalInrollment5.setText(this.studentEnrollment.getLstStudentClass().get(4).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(4).getTotalEnrollment());
                this.level_totalboy6.setText(this.studentEnrollment.getLstStudentClass().get(5).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(5).getTotalBoysEnrollment());
                this.level_totalgirl6.setText(this.studentEnrollment.getLstStudentClass().get(5).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(5).getTotalGirlsEnrollment());
                this.level_totalInrollment6.setText(this.studentEnrollment.getLstStudentClass().get(5).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(5).getTotalEnrollment());
                this.level_totalboy7.setText(this.studentEnrollment.getLstStudentClass().get(6).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(6).getTotalBoysEnrollment());
                this.level_totalgirl7.setText(this.studentEnrollment.getLstStudentClass().get(6).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(6).getTotalGirlsEnrollment());
                this.level_totalInrollment7.setText(this.studentEnrollment.getLstStudentClass().get(6).getTotalEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(6).getTotalEnrollment());
                this.level_totalboy8.setText(this.studentEnrollment.getLstStudentClass().get(7).getTotalBoysEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(7).getTotalBoysEnrollment());
                this.level_totalgirl8.setText(this.studentEnrollment.getLstStudentClass().get(7).getTotalGirlsEnrollment() == null ? "0" : this.studentEnrollment.getLstStudentClass().get(7).getTotalGirlsEnrollment());
                this.level_totalInrollment8.setText(this.studentEnrollment.getLstStudentClass().get(7).getTotalEnrollment() != null ? this.studentEnrollment.getLstStudentClass().get(7).getTotalEnrollment() : "0");
            }
            StudentEnrollment studentEnrollment10 = this.studentEnrollment;
            if (studentEnrollment10 != null && studentEnrollment10.getIsFinalized() != null && this.studentEnrollment.getIsFinalized().equalsIgnoreCase("true")) {
                this.level_totalboy1.setEnabled(false);
                this.level_totalgirl1.setEnabled(false);
                this.level_totalboy2.setEnabled(false);
                this.level_totalgirl2.setEnabled(false);
                this.level_totalboy3.setEnabled(false);
                this.level_totalgirl3.setEnabled(false);
                this.level_totalboy4.setEnabled(false);
                this.level_totalgirl4.setEnabled(false);
                this.level_totalboy5.setEnabled(false);
                this.level_totalgirl5.setEnabled(false);
                this.level_totalboy6.setEnabled(false);
                this.level_totalgirl6.setEnabled(false);
                this.level_totalboy7.setEnabled(false);
                this.level_totalgirl7.setEnabled(false);
                this.level_totalboy8.setEnabled(false);
                this.level_totalgirl8.setEnabled(false);
                this.spforschoolcategory.setEnabled(false);
                this.udicecode.setEnabled(false);
            }
        }
        this.checkbox_accept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                if (z2) {
                    student_enrollment_activity.this.llforattachment.setVisibility(0);
                    student_enrollment_activity.this.btn_save_as_draft.setVisibility(8);
                    student_enrollment_activity.this.btn_final_save.setVisibility(0);
                    student_enrollment_activity.this.saveasfinal = "true";
                    return;
                }
                student_enrollment_activity.this.llforattachment.setVisibility(8);
                student_enrollment_activity.this.btn_save_as_draft.setVisibility(0);
                student_enrollment_activity.this.btn_final_save.setVisibility(8);
                student_enrollment_activity.this.saveasfinal = "false";
            }
        });
        this.imageforattachment.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(student_enrollment_activity.this);
                sweetAlertDialog.changeAlertType(3);
                sweetAlertDialog.setConfirmText("Yes");
                sweetAlertDialog.setCancelText("No");
                sweetAlertDialog.setCancelable(true);
                sweetAlertDialog.setTitleText("Do you want to upload pdf file ?");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.7.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                        sweetAlertDialog2.dismissWithAnimation();
                        try {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add("android.permission.READ_EXTERNAL_STORAGE");
                            arrayList2.add("android.permission.WRITE_EXTERNAL_STORAGE");
                            if (student_enrollment_activity.this.checkAndRequestPermissions(arrayList2, CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB)) {
                                student_enrollment_activity.this.ChoosePdf();
                            } else {
                                student_enrollment_activity.this.getAllPermissionone();
                            }
                        } catch (Exception unused) {
                        }
                    }
                }).show();
                sweetAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.7.2
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
            }
        });
        this.btn_save_as_draft.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (student_enrollment_activity.this.spforschoolcategory != null && student_enrollment_activity.this.spforschoolcategory.getSelectedItem() != null && student_enrollment_activity.this.spforschoolcategory.getSelectedItem().toString() != null && !student_enrollment_activity.this.spforschoolcategory.getSelectedItem().toString().trim().equalsIgnoreCase("--select--")) {
                    student_enrollment_activity.this.saveasfinaldata("0");
                } else {
                    Toast.makeText(student_enrollment_activity.this, "Please select School Category", 0).show();
                }
            }
        });
        this.btn_final_save.setOnClickListener(new View.OnClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (student_enrollment_activity.this.spforschoolcategory != null && student_enrollment_activity.this.spforschoolcategory.getSelectedItem() != null && student_enrollment_activity.this.spforschoolcategory.getSelectedItem().toString() != null && !student_enrollment_activity.this.spforschoolcategory.getSelectedItem().toString().trim().equalsIgnoreCase("--select--")) {
                    student_enrollment_activity.this.saveasfinaldata("1");
                } else {
                    Toast.makeText(student_enrollment_activity.this, "Please select School Category", 0).show();
                }
            }
        });
        this.level_totalboy1.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.10
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl1.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl1.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl1.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl1.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl1.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.11
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy1.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy1.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy1.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy1.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment1.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy2.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.12
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl2.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl2.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl2.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl2.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl2.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.13
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy2.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy2.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy2.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy2.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment2.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy3.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.14
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl3.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl3.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl3.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl3.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl3.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.15
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy3.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy3.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy3.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy3.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment3.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy4.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.16
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl4.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl4.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl4.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl4.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl4.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.17
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy4.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy4.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy4.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy4.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment4.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy5.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.18
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl5.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl5.getText().toString())) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl5.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl5.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl5.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.19
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy5.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy5.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy5.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy5.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment5.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy6.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.20
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl6.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl6.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl6.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl6.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl6.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.21
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy6.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy6.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy6.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy6.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment6.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy7.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.22
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl7.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl7.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl7.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl7.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl7.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.23
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy7.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy7.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy7.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy7.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment7.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalboy8.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.24
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalgirl8.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalgirl8.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalgirl8.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalgirl8.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
        this.level_totalgirl8.addTextChangedListener(new TextWatcher() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.25
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    if (!student_enrollment_activity.this.level_totalboy8.getText().toString().equalsIgnoreCase("")) {
                        student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt(editable.toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalboy8.getText().toString())) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    } else {
                        student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt(editable.toString()) + Integer.parseInt("0")) + "");
                        student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                        return;
                    }
                }
                if (!student_enrollment_activity.this.level_totalboy8.getText().toString().equalsIgnoreCase("")) {
                    student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt("0") + Integer.parseInt(student_enrollment_activity.this.level_totalboy8.getText().toString())) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                } else {
                    student_enrollment_activity.this.level_totalInrollment8.setText((Integer.parseInt("0") + Integer.parseInt("0")) + "");
                    student_enrollment_activity.this.totalinroolment.setText((Integer.parseInt(student_enrollment_activity.this.level_totalInrollment1.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment1.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment2.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment2.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment3.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment3.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment4.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment4.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment5.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment5.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment6.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment6.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment7.getText().toString() == null ? "0" : student_enrollment_activity.this.level_totalInrollment7.getText().toString()) + Integer.parseInt(student_enrollment_activity.this.level_totalInrollment8.getText().toString() != null ? student_enrollment_activity.this.level_totalInrollment8.getText().toString() : "0")) + "");
                }
            }
        });
    }

    private void showAlertMessase() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setTitleText(getResources().getString(C2778R.string.title_data));
        sweetAlertDialog.setContentText(getResources().getString(C2778R.string.txtcontent2));
        sweetAlertDialog.setConfirmText(getResources().getString(C2778R.string.dialog_ok));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.technosys.StudentEnrollment.StudentInroolmentCount.student_enrollment_activity.26
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    void findbyid() {
        this.Districtforsch = (TextInputEditText) findViewById(C2778R.id.Districtforsch);
        this.blockortownsch = (TextInputEditText) findViewById(C2778R.id.blockortownsch);
        this.areatype = (TextInputEditText) findViewById(C2778R.id.areatype);
        this.session = (TextInputEditText) findViewById(C2778R.id.session);
        this.schname = (TextInputEditText) findViewById(C2778R.id.schname);
        this.udicecode = (TextInputEditText) findViewById(C2778R.id.udicecode);
        this.totalinroolment = (TextInputEditText) findViewById(C2778R.id.totalinroolment);
        this.spforschoolcategory = (Spinner) findViewById(C2778R.id.spforschoolcategory);
        this.level_totalboy1 = (EditText) findViewById(C2778R.id.level_totalboy1);
        this.level_totalgirl1 = (EditText) findViewById(C2778R.id.level_totalgirl1);
        this.level_totalboy2 = (EditText) findViewById(C2778R.id.level_totalboy2);
        this.level_totalgirl2 = (EditText) findViewById(C2778R.id.level_totalgirl2);
        this.level_totalboy3 = (EditText) findViewById(C2778R.id.level_totalboy3);
        this.level_totalgirl3 = (EditText) findViewById(C2778R.id.level_totalgirl3);
        this.level_totalboy4 = (EditText) findViewById(C2778R.id.level_totalboy4);
        this.level_totalgirl4 = (EditText) findViewById(C2778R.id.level_totalgirl4);
        this.level_totalboy5 = (EditText) findViewById(C2778R.id.level_totalboy5);
        this.level_totalgirl5 = (EditText) findViewById(C2778R.id.level_totalgirl5);
        this.level_totalboy6 = (EditText) findViewById(C2778R.id.level_totalboy6);
        this.level_totalgirl6 = (EditText) findViewById(C2778R.id.level_totalgirl6);
        this.level_totalboy7 = (EditText) findViewById(C2778R.id.level_totalboy7);
        this.level_totalgirl7 = (EditText) findViewById(C2778R.id.level_totalgirl7);
        this.level_totalboy8 = (EditText) findViewById(C2778R.id.level_totalboy8);
        this.level_totalgirl8 = (EditText) findViewById(C2778R.id.level_totalgirl8);
        this.imageforattachment = (ImageView) findViewById(C2778R.id.imageforattachment);
        this.llforattachment = (LinearLayout) findViewById(C2778R.id.llforattachment);
        this.btn_save_as_draft = (Button) findViewById(C2778R.id.btn_save_as_draft);
        this.btn_final_save = (Button) findViewById(C2778R.id.btn_final_save);
        this.checkbox_accept = (CheckBox) findViewById(C2778R.id.checkbox_accept);
        this.level_totalInrollment1 = (TextView) findViewById(C2778R.id.level_totalInrollment1);
        this.level_totalInrollment2 = (TextView) findViewById(C2778R.id.level_totalInrollment2);
        this.level_totalInrollment3 = (TextView) findViewById(C2778R.id.level_totalInrollment3);
        this.level_totalInrollment4 = (TextView) findViewById(C2778R.id.level_totalInrollment4);
        this.level_totalInrollment5 = (TextView) findViewById(C2778R.id.level_totalInrollment5);
        this.level_totalInrollment6 = (TextView) findViewById(C2778R.id.level_totalInrollment6);
        this.level_totalInrollment7 = (TextView) findViewById(C2778R.id.level_totalInrollment7);
        this.level_totalInrollment8 = (TextView) findViewById(C2778R.id.level_totalInrollment8);
        this.pdfname = (TextView) findViewById(C2778R.id.pdfname);
        this.ivforclear = (ImageView) findViewById(C2778R.id.ivforclear);
        this.tabrow1 = (TableRow) findViewById(C2778R.id.tabrow1);
        this.tabrow2 = (TableRow) findViewById(C2778R.id.tabrow2);
        this.tabrow3 = (TableRow) findViewById(C2778R.id.tabrow3);
        this.tabrow4 = (TableRow) findViewById(C2778R.id.tabrow4);
        this.tabrow5 = (TableRow) findViewById(C2778R.id.tabrow5);
        this.tabrow6 = (TableRow) findViewById(C2778R.id.tabrow6);
        this.tabrow7 = (TableRow) findViewById(C2778R.id.tabrow7);
        this.tabrow8 = (TableRow) findViewById(C2778R.id.tabrow8);
        this.btn_unsync = (Button) findViewById(C2778R.id.btn_unsync);
        this.btn_sync = (Button) findViewById(C2778R.id.btn_sync);
        this.ll_Sync = (LinearLayout) findViewById(C2778R.id.ll_Sync);
        this.Im_Sync = (ImageView) findViewById(C2778R.id.Im_Sync);
        this.ll_Button = (LinearLayout) findViewById(C2778R.id.ll_Button);
        this.tv_Sync = (TextView) findViewById(C2778R.id.tv_Sync);
        this.pdfView = (TextView) findViewById(C2778R.id.pdfView);
        this.tv_info = (TextView) findViewById(C2778R.id.tv_info);
        this.Im_FileSync = (ImageView) findViewById(C2778R.id.Im_FileSync);
    }

    void saveasfinaldata(String str) {
        int i;
        String str2;
        String str3;
        String str4;
        if (this.udicecode.getText().toString().trim().equalsIgnoreCase("")) {
            this.udicecode.setError("empty");
            this.udicecode.requestFocus();
            return;
        }
        if (this.udicecode.getText().toString().length() < 11) {
            this.udicecode.setError("Fill Correct UdiseCode");
            this.udicecode.requestFocus();
            return;
        }
        if (this.level_totalboy1.getVisibility() == 0 && this.level_totalboy1.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy1.setError("empty");
            this.level_totalboy1.requestFocus();
            return;
        }
        if (this.level_totalgirl1.getVisibility() == 0 && this.level_totalgirl1.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl1.setError("empty");
            this.level_totalgirl1.requestFocus();
            return;
        }
        if (this.level_totalboy2.getVisibility() == 0 && this.level_totalboy2.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy2.setError("empty");
            this.level_totalboy2.requestFocus();
            return;
        }
        if (this.level_totalgirl2.getVisibility() == 0 && this.level_totalgirl2.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl2.setError("empty");
            this.level_totalgirl2.requestFocus();
            return;
        }
        if (this.level_totalboy3.getVisibility() == 0 && this.level_totalboy3.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy3.setError("empty");
            this.level_totalboy3.requestFocus();
            return;
        }
        if (this.level_totalgirl3.getVisibility() == 0 && this.level_totalgirl3.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl3.setError("empty");
            this.level_totalgirl3.requestFocus();
            return;
        }
        if (this.level_totalboy4.getVisibility() == 0 && this.level_totalboy4.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy4.setError("empty");
            this.level_totalboy4.requestFocus();
            return;
        }
        if (this.level_totalgirl4.getVisibility() == 0 && this.level_totalgirl4.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl4.setError("empty");
            this.level_totalgirl4.requestFocus();
            return;
        }
        if (this.level_totalboy5.getVisibility() == 0 && this.level_totalboy5.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy5.setError("empty");
            this.level_totalboy5.requestFocus();
            return;
        }
        if (this.level_totalgirl5.getVisibility() == 0 && this.level_totalgirl5.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl5.setError("empty");
            this.level_totalgirl5.requestFocus();
            return;
        }
        if (this.level_totalboy6.getVisibility() == 0 && this.level_totalboy6.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy6.setError("empty");
            this.level_totalboy6.requestFocus();
            return;
        }
        if (this.level_totalgirl6.getVisibility() == 0 && this.level_totalgirl6.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl6.setError("empty");
            this.level_totalgirl6.requestFocus();
            return;
        }
        if (this.level_totalboy7.getVisibility() == 0 && this.level_totalboy7.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy7.setError("empty");
            this.level_totalboy7.requestFocus();
            return;
        }
        if (this.level_totalgirl7.getVisibility() == 0 && this.level_totalgirl7.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl7.setError("empty");
            this.level_totalgirl7.requestFocus();
            return;
        }
        if (this.level_totalboy8.getVisibility() == 0 && this.level_totalboy8.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalboy8.setError("empty");
            this.level_totalboy8.requestFocus();
            return;
        }
        if (this.level_totalgirl8.getVisibility() == 0 && this.level_totalgirl8.getText().toString().trim().equalsIgnoreCase("")) {
            this.level_totalgirl8.setError("empty");
            this.level_totalgirl8.requestFocus();
            return;
        }
        if (str.equalsIgnoreCase("0")) {
            StudentEnrollment studentEnrollment = new StudentEnrollment();
            studentEnrollment.setAreaName(this.studentEnrollment.getAreaName() == null ? "0" : this.studentEnrollment.getAreaName());
            studentEnrollment.setAreaId(this.studentEnrollment.getAreaId() == null ? "0" : this.studentEnrollment.getAreaId());
            studentEnrollment.setBlockTownId(this.studentEnrollment.getBlockTownId() == null ? "0" : this.studentEnrollment.getBlockTownId());
            studentEnrollment.setBlockTownName(this.blockortownsch.getText().toString().trim());
            studentEnrollment.setDistrictId(this.studentEnrollment.getDistrictId() == null ? "0" : this.studentEnrollment.getDistrictId());
            studentEnrollment.setDistrictName(this.Districtforsch.getText().toString().trim());
            studentEnrollment.setFilename("");
            studentEnrollment.setPhysicalFilePath("");
            studentEnrollment.setFileId("");
            studentEnrollment.setIsSync("false");
            studentEnrollment.setEnrolledId(this.studentEnrollment.getEnrolledId() == null ? "0" : this.studentEnrollment.getEnrolledId());
            studentEnrollment.setIsDraft("true");
            studentEnrollment.setSchool_CodeId(this.studentEnrollment.getSchool_CodeId() == null ? "0" : this.studentEnrollment.getSchool_CodeId());
            studentEnrollment.setUdiseCode(this.udicecode.getText().toString());
            studentEnrollment.setTotalEnrollment(this.totalinroolment.getText().toString());
            studentEnrollment.setSchool_CodeName(this.schname.getText().toString());
            studentEnrollment.setSessionId(this.studentEnrollment.getSessionId() != null ? this.studentEnrollment.getSessionId() : "0");
            studentEnrollment.setSessionName(this.session.getText().toString());
            studentEnrollment.setIsFinalized("false");
            studentEnrollment.setTeacher_Id(this.userCredential.getPerson_Id() + "");
            studentEnrollment.setDesignation_Id(this.userCredential.getDesignation_Id());
            studentEnrollment.setSchoolTypeId(this.studentEnrollment.getSchoolTypeId());
            try {
                str3 = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                str3 = "";
            }
            if (str3 != null && !str3.equalsIgnoreCase("")) {
                studentEnrollment.setApp_Version(str3);
            } else {
                studentEnrollment.setApp_Version("");
            }
            studentEnrollment.setSubmissionDate(new SimpleDateFormat("dd/MM/yyyy kk:mm:ss", new Locale("en")).format(new Date()));
            if (this.spinnerid != null && (str4 = this.spinnername) != null && !str4.equalsIgnoreCase("")) {
                studentEnrollment.setSchoolCategoryName(this.spinnername);
                studentEnrollment.setSchoolCategoryId(this.spinnerid);
            } else {
                studentEnrollment.setSchoolCategoryName(this.studentEnrollment.getSchoolCategoryName() == null ? "N/A" : this.studentEnrollment.getSchoolCategoryName());
                studentEnrollment.setSchoolCategoryId(this.studentEnrollment.getSchoolCategoryId() != null ? this.studentEnrollment.getSchoolCategoryId() : "N/A");
            }
            studentEnrollment.setSchoolClassTypeActual_Id(this.studentEnrollment.getSchoolClassTypeActual_Id());
            if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase("1")) {
                this.lstcall.clear();
                for (int i2 = 0; i2 <= 4; i2++) {
                    StudentClass studentClass = new StudentClass();
                    if (i2 == 0) {
                        studentClass.setTotalBoysEnrollment(this.level_totalboy1.getText().toString().trim());
                        studentClass.setTotalGirlsEnrollment(this.level_totalgirl1.getText().toString().trim());
                        studentClass.setTotalEnrollment(this.level_totalInrollment1.getText().toString().trim());
                        this.lstcall.add(studentClass);
                    } else if (i2 == 1) {
                        studentClass.setTotalBoysEnrollment(this.level_totalboy2.getText().toString().trim());
                        studentClass.setTotalGirlsEnrollment(this.level_totalgirl2.getText().toString().trim());
                        studentClass.setTotalEnrollment(this.level_totalInrollment2.getText().toString().trim());
                        this.lstcall.add(studentClass);
                    } else if (i2 == 2) {
                        studentClass.setTotalBoysEnrollment(this.level_totalboy3.getText().toString().trim());
                        studentClass.setTotalGirlsEnrollment(this.level_totalgirl3.getText().toString().trim());
                        studentClass.setTotalEnrollment(this.level_totalInrollment3.getText().toString().trim());
                        this.lstcall.add(studentClass);
                    } else if (i2 == 3) {
                        studentClass.setTotalBoysEnrollment(this.level_totalboy4.getText().toString().trim());
                        studentClass.setTotalGirlsEnrollment(this.level_totalgirl4.getText().toString().trim());
                        studentClass.setTotalEnrollment(this.level_totalInrollment4.getText().toString().trim());
                        this.lstcall.add(studentClass);
                    } else if (i2 == 4) {
                        studentClass.setTotalBoysEnrollment(this.level_totalboy5.getText().toString().trim());
                        studentClass.setTotalGirlsEnrollment(this.level_totalgirl5.getText().toString().trim());
                        studentClass.setTotalEnrollment(this.level_totalInrollment5.getText().toString().trim());
                        this.lstcall.add(studentClass);
                    }
                }
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.lstcall.clear();
                for (int i3 = 0; i3 <= 2; i3++) {
                    StudentClass studentClass2 = new StudentClass();
                    if (i3 == 0) {
                        studentClass2.setTotalBoysEnrollment(this.level_totalboy6.getText().toString().trim());
                        studentClass2.setTotalGirlsEnrollment(this.level_totalgirl6.getText().toString().trim());
                        studentClass2.setTotalEnrollment(this.level_totalInrollment6.getText().toString().trim());
                        this.lstcall.add(studentClass2);
                    } else if (i3 == 1) {
                        studentClass2.setTotalBoysEnrollment(this.level_totalboy7.getText().toString().trim());
                        studentClass2.setTotalGirlsEnrollment(this.level_totalgirl7.getText().toString().trim());
                        studentClass2.setTotalEnrollment(this.level_totalInrollment7.getText().toString().trim());
                        this.lstcall.add(studentClass2);
                    } else if (i3 == 2) {
                        studentClass2.setTotalBoysEnrollment(this.level_totalboy8.getText().toString().trim());
                        studentClass2.setTotalGirlsEnrollment(this.level_totalgirl8.getText().toString().trim());
                        studentClass2.setTotalEnrollment(this.level_totalInrollment8.getText().toString().trim());
                        this.lstcall.add(studentClass2);
                    }
                }
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
                this.lstcall.clear();
                for (int i4 = 0; i4 <= 7; i4++) {
                    StudentClass studentClass3 = new StudentClass();
                    if (i4 == 0) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy1.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl1.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment1.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 1) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy2.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl2.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment2.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 2) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy3.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl3.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment3.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 3) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy4.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl4.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment4.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 4) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy5.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl5.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment5.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 5) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy6.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl6.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment6.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 6) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy7.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl7.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment7.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    } else if (i4 == 7) {
                        studentClass3.setTotalBoysEnrollment(this.level_totalboy8.getText().toString().trim());
                        studentClass3.setTotalGirlsEnrollment(this.level_totalgirl8.getText().toString().trim());
                        studentClass3.setTotalEnrollment(this.level_totalInrollment8.getText().toString().trim());
                        this.lstcall.add(studentClass3);
                    }
                }
            }
            studentEnrollment.setLstStudentClass(this.lstcall);
            CoronaDataSource coronaDataSource = new CoronaDataSource(this);
            coronaDataSource.open();
            this.studentEnrollmentforchecksaving = coronaDataSource.selectissaveasdraftissynced();
            coronaDataSource.close();
            StudentEnrollment studentEnrollment2 = this.studentEnrollmentforchecksaving;
            if (studentEnrollment2 != null && studentEnrollment2.getSavingcommonid() != null) {
                coronaDataSource.open();
                coronaDataSource.update_Tbl_StudentEnrollmentByTeacher(studentEnrollment, this.studentEnrollmentforchecksaving.getSavingcommonid());
                StudentEnrollment studentEnrollmentDetailtocommonid = coronaDataSource.getStudentEnrollmentDetailtocommonid(this.studentEnrollmentforchecksaving.getSavingcommonid());
                coronaDataSource.close();
                if (AndroidUtils.checkYourMobileDataConnection(this)) {
                    new ThreadForSavingwithFile(this, studentEnrollmentDetailtocommonid).execute(new Void[0]);
                } else {
                    Intent intent = new Intent(this, (Class<?>) student_enrollment_dashboard.class);
                    intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                    intent.addFlags(32768);
                    intent.addFlags(268435456);
                    startActivity(intent);
                    Toast.makeText(this, "No Internet Connectivity", 0).show();
                }
            } else {
                coronaDataSource.open();
                coronaDataSource.insert_tbl_UserProfile(studentEnrollment);
                StudentEnrollment studentEnrollmentDetailtocommonid2 = coronaDataSource.getStudentEnrollmentDetailtocommonid(this.studentEnrollmentforchecksaving.getSavingcommonid());
                coronaDataSource.close();
                if (AndroidUtils.checkYourMobileDataConnection(this)) {
                    new ThreadForSavingwithFile(this, studentEnrollmentDetailtocommonid2).execute(new Void[0]);
                } else {
                    Intent intent2 = new Intent(this, (Class<?>) student_enrollment_dashboard.class);
                    intent2.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                    intent2.addFlags(32768);
                    intent2.addFlags(268435456);
                    startActivity(intent2);
                    Toast.makeText(this, "No Internet Connectivity", 0).show();
                }
            }
            if (AndroidUtils.checkYourMobileDataConnection(this)) {
                return;
            }
            Toast.makeText(this, "No Internet Connectivity", 0).show();
            return;
        }
        if (str.equalsIgnoreCase("1")) {
            if (this.pdfname.getVisibility() == 8) {
                Toast.makeText(this, "Please Choose pdf file maximum size 5mb", 0).show();
                return;
            }
            StudentEnrollment studentEnrollment3 = new StudentEnrollment();
            studentEnrollment3.setAreaName(this.studentEnrollment.getAreaName() == null ? "0" : this.studentEnrollment.getAreaName());
            studentEnrollment3.setAreaId(this.studentEnrollment.getAreaId() == null ? "0" : this.studentEnrollment.getAreaId());
            studentEnrollment3.setBlockTownId(this.studentEnrollment.getBlockTownId() == null ? "0" : this.studentEnrollment.getBlockTownId());
            studentEnrollment3.setBlockTownName(this.blockortownsch.getText().toString().trim());
            studentEnrollment3.setDistrictId(this.studentEnrollment.getDistrictId() == null ? "0" : this.studentEnrollment.getDistrictId());
            studentEnrollment3.setDistrictName(this.Districtforsch.getText().toString().trim());
            studentEnrollment3.setFilename(this.fileName);
            studentEnrollment3.setPhysicalFilePath(this.filepath);
            studentEnrollment3.setFile_SizeInMB("1024");
            studentEnrollment3.setServerGeneratedFileName(this.ServerGenerateFileName);
            studentEnrollment3.setFileExtension(".pdf");
            studentEnrollment3.setIsSync("false");
            studentEnrollment3.setIsFileSync("false");
            studentEnrollment3.setFileId(this.studentEnrollment.getFileId());
            studentEnrollment3.setEnrolledId(this.studentEnrollment.getEnrolledId() == null ? "0" : this.studentEnrollment.getEnrolledId());
            studentEnrollment3.setIsDraft("true");
            studentEnrollment3.setSchool_CodeId(this.studentEnrollment.getSchool_CodeId() == null ? "0" : this.studentEnrollment.getSchool_CodeId());
            studentEnrollment3.setUdiseCode(this.udicecode.getText().toString());
            studentEnrollment3.setTotalEnrollment(this.totalinroolment.getText().toString());
            studentEnrollment3.setSchool_CodeName(this.schname.getText().toString());
            studentEnrollment3.setSessionId(this.studentEnrollment.getSessionId() != null ? this.studentEnrollment.getSessionId() : "0");
            studentEnrollment3.setSessionName(this.session.getText().toString());
            studentEnrollment3.setIsFinalized("true");
            studentEnrollment3.setTeacher_Id(this.userCredential.getPerson_Id() + "");
            studentEnrollment3.setDesignation_Id(this.userCredential.getDesignation_Id());
            studentEnrollment3.setSchoolTypeId(this.studentEnrollment.getSchoolTypeId());
            studentEnrollment3.setSubmissionDate(new SimpleDateFormat("dd/MM/yyyy kk:mm:ss", new Locale("en")).format(new Date()));
            if (this.spinnerid != null && (str2 = this.spinnername) != null && !str2.equalsIgnoreCase("")) {
                studentEnrollment3.setSchoolCategoryName(this.spinnername);
                studentEnrollment3.setSchoolCategoryId(this.spinnerid);
            } else {
                studentEnrollment3.setSchoolCategoryName(this.studentEnrollment.getSchoolCategoryName() == null ? "N/A" : this.studentEnrollment.getSchoolCategoryName());
                studentEnrollment3.setSchoolCategoryId(this.studentEnrollment.getSchoolCategoryId() != null ? this.studentEnrollment.getSchoolCategoryId() : "N/A");
            }
            studentEnrollment3.setSchoolClassTypeActual_Id(this.studentEnrollment.getSchoolClassTypeActual_Id());
            if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase("1")) {
                this.lstcall.clear();
                for (int i5 = 0; i5 <= 4; i5++) {
                    StudentClass studentClass4 = new StudentClass();
                    if (i5 == 0) {
                        studentClass4.setTotalBoysEnrollment(this.level_totalboy1.getText().toString().trim());
                        studentClass4.setTotalGirlsEnrollment(this.level_totalgirl1.getText().toString().trim());
                        studentClass4.setTotalEnrollment(this.level_totalInrollment1.getText().toString().trim());
                        this.lstcall.add(studentClass4);
                    } else if (i5 == 1) {
                        studentClass4.setTotalBoysEnrollment(this.level_totalboy2.getText().toString().trim());
                        studentClass4.setTotalGirlsEnrollment(this.level_totalgirl2.getText().toString().trim());
                        studentClass4.setTotalEnrollment(this.level_totalInrollment2.getText().toString().trim());
                        this.lstcall.add(studentClass4);
                    } else if (i5 == 2) {
                        studentClass4.setTotalBoysEnrollment(this.level_totalboy3.getText().toString().trim());
                        studentClass4.setTotalGirlsEnrollment(this.level_totalgirl3.getText().toString().trim());
                        studentClass4.setTotalEnrollment(this.level_totalInrollment3.getText().toString().trim());
                        this.lstcall.add(studentClass4);
                    } else if (i5 == 3) {
                        studentClass4.setTotalBoysEnrollment(this.level_totalboy4.getText().toString().trim());
                        studentClass4.setTotalGirlsEnrollment(this.level_totalgirl4.getText().toString().trim());
                        studentClass4.setTotalEnrollment(this.level_totalInrollment4.getText().toString().trim());
                        this.lstcall.add(studentClass4);
                    } else if (i5 == 4) {
                        studentClass4.setTotalBoysEnrollment(this.level_totalboy5.getText().toString().trim());
                        studentClass4.setTotalGirlsEnrollment(this.level_totalgirl5.getText().toString().trim());
                        studentClass4.setTotalEnrollment(this.level_totalInrollment5.getText().toString().trim());
                        this.lstcall.add(studentClass4);
                    }
                }
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.lstcall.clear();
                for (int i6 = 0; i6 <= 2; i6++) {
                    StudentClass studentClass5 = new StudentClass();
                    if (i6 == 0) {
                        studentClass5.setTotalBoysEnrollment(this.level_totalboy6.getText().toString().trim());
                        studentClass5.setTotalGirlsEnrollment(this.level_totalgirl6.getText().toString().trim());
                        studentClass5.setTotalEnrollment(this.level_totalInrollment6.getText().toString().trim());
                        this.lstcall.add(studentClass5);
                    } else if (i6 == 1) {
                        studentClass5.setTotalBoysEnrollment(this.level_totalboy7.getText().toString().trim());
                        studentClass5.setTotalGirlsEnrollment(this.level_totalgirl7.getText().toString().trim());
                        studentClass5.setTotalEnrollment(this.level_totalInrollment7.getText().toString().trim());
                        this.lstcall.add(studentClass5);
                    } else if (i6 == 2) {
                        studentClass5.setTotalBoysEnrollment(this.level_totalboy8.getText().toString().trim());
                        studentClass5.setTotalGirlsEnrollment(this.level_totalgirl8.getText().toString().trim());
                        studentClass5.setTotalEnrollment(this.level_totalInrollment8.getText().toString().trim());
                        this.lstcall.add(studentClass5);
                    }
                }
            } else if (this.studentEnrollment.getSchoolClassTypeActual_Id() != null && this.studentEnrollment.getSchoolClassTypeActual_Id().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
                this.lstcall.clear();
                int i7 = 0;
                int i8 = 7;
                while (i7 <= i8) {
                    StudentClass studentClass6 = new StudentClass();
                    if (i7 == 0) {
                        studentClass6.setTotalBoysEnrollment(this.level_totalboy1.getText().toString().trim());
                        studentClass6.setTotalGirlsEnrollment(this.level_totalgirl1.getText().toString().trim());
                        studentClass6.setTotalEnrollment(this.level_totalInrollment1.getText().toString().trim());
                        this.lstcall.add(studentClass6);
                    } else if (i7 == 1) {
                        studentClass6.setTotalBoysEnrollment(this.level_totalboy2.getText().toString().trim());
                        studentClass6.setTotalGirlsEnrollment(this.level_totalgirl2.getText().toString().trim());
                        studentClass6.setTotalEnrollment(this.level_totalInrollment2.getText().toString().trim());
                        this.lstcall.add(studentClass6);
                    } else if (i7 == 2) {
                        studentClass6.setTotalBoysEnrollment(this.level_totalboy3.getText().toString().trim());
                        studentClass6.setTotalGirlsEnrollment(this.level_totalgirl3.getText().toString().trim());
                        studentClass6.setTotalEnrollment(this.level_totalInrollment3.getText().toString().trim());
                        this.lstcall.add(studentClass6);
                        i = 7;
                        i7++;
                        i8 = i;
                    } else {
                        if (i7 == 3) {
                            studentClass6.setTotalBoysEnrollment(this.level_totalboy4.getText().toString().trim());
                            studentClass6.setTotalGirlsEnrollment(this.level_totalgirl4.getText().toString().trim());
                            studentClass6.setTotalEnrollment(this.level_totalInrollment4.getText().toString().trim());
                            this.lstcall.add(studentClass6);
                        } else if (i7 == 4) {
                            studentClass6.setTotalBoysEnrollment(this.level_totalboy5.getText().toString().trim());
                            studentClass6.setTotalGirlsEnrollment(this.level_totalgirl5.getText().toString().trim());
                            studentClass6.setTotalEnrollment(this.level_totalInrollment5.getText().toString().trim());
                            this.lstcall.add(studentClass6);
                        } else {
                            if (i7 == 5) {
                                studentClass6.setTotalBoysEnrollment(this.level_totalboy6.getText().toString().trim());
                                studentClass6.setTotalGirlsEnrollment(this.level_totalgirl6.getText().toString().trim());
                                studentClass6.setTotalEnrollment(this.level_totalInrollment6.getText().toString().trim());
                                this.lstcall.add(studentClass6);
                            } else if (i7 == 6) {
                                studentClass6.setTotalBoysEnrollment(this.level_totalboy7.getText().toString().trim());
                                studentClass6.setTotalGirlsEnrollment(this.level_totalgirl7.getText().toString().trim());
                                studentClass6.setTotalEnrollment(this.level_totalInrollment7.getText().toString().trim());
                                this.lstcall.add(studentClass6);
                            } else {
                                i = 7;
                                if (i7 == 7) {
                                    studentClass6.setTotalBoysEnrollment(this.level_totalboy8.getText().toString().trim());
                                    studentClass6.setTotalGirlsEnrollment(this.level_totalgirl8.getText().toString().trim());
                                    studentClass6.setTotalEnrollment(this.level_totalInrollment8.getText().toString().trim());
                                    this.lstcall.add(studentClass6);
                                }
                                i7++;
                                i8 = i;
                            }
                            i = 7;
                            i7++;
                            i8 = i;
                        }
                        i = 7;
                        i7++;
                        i8 = i;
                    }
                    i = 7;
                    i7++;
                    i8 = i;
                }
            }
            studentEnrollment3.setLstStudentClass(this.lstcall);
            CoronaDataSource coronaDataSource2 = new CoronaDataSource(this);
            coronaDataSource2.open();
            this.studentEnrollmentforchecksaving = coronaDataSource2.selectissaveasdraftissynced();
            coronaDataSource2.close();
            StudentEnrollment studentEnrollment4 = this.studentEnrollmentforchecksaving;
            if (studentEnrollment4 != null && studentEnrollment4.getSavingcommonid() != null) {
                coronaDataSource2.open();
                coronaDataSource2.update_Tbl_StudentEnrollmentByTeacher(studentEnrollment3, this.studentEnrollmentforchecksaving.getSavingcommonid());
                StudentEnrollment studentEnrollmentDetailtocommonid3 = coronaDataSource2.getStudentEnrollmentDetailtocommonid(this.studentEnrollmentforchecksaving.getSavingcommonid());
                coronaDataSource2.close();
                if (AndroidUtils.checkYourMobileDataConnection(this)) {
                    new ThreadForSavingwithFile(this, studentEnrollmentDetailtocommonid3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    Intent intent3 = new Intent(this, (Class<?>) student_enrollment_dashboard.class);
                    intent3.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                    intent3.addFlags(32768);
                    intent3.addFlags(268435456);
                    startActivity(intent3);
                    Toast.makeText(this, "No Internet Connectivity", 0).show();
                }
            } else {
                coronaDataSource2.open();
                coronaDataSource2.insert_tbl_UserProfile(studentEnrollment3);
                StudentEnrollment studentEnrollmentDetailtocommonid4 = coronaDataSource2.getStudentEnrollmentDetailtocommonid(this.studentEnrollmentforchecksaving.getSavingcommonid());
                coronaDataSource2.close();
                if (AndroidUtils.checkYourMobileDataConnection(this)) {
                    new ThreadForSavingwithFile(this, studentEnrollmentDetailtocommonid4).execute(new Void[0]);
                } else {
                    Intent intent4 = new Intent(this, (Class<?>) student_enrollment_dashboard.class);
                    intent4.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                    intent4.addFlags(32768);
                    intent4.addFlags(268435456);
                    startActivity(intent4);
                    Toast.makeText(this, "No Internet Connectivity", 0).show();
                }
            }
            if (AndroidUtils.checkYourMobileDataConnection(this)) {
                return;
            }
            Toast.makeText(this, "No Internet Connectivity", 0).show();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, (Class<?>) student_enrollment_dashboard.class);
        intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllPermissionone() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        for (int i = 0; i < arrayList.size(); i++) {
            if (ActivityCompat.checkSelfPermission(this, (String) arrayList.get(i)) != 0) {
                arrayList2.add((String) arrayList.get(i));
            }
        }
        if (arrayList2.size() > 0) {
            String[] strArr = new String[arrayList2.size()];
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                strArr[i2] = (String) arrayList2.get(i2);
            }
            ActivityCompat.requestPermissions(this, strArr, 102);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkAndRequestPermissions(ArrayList<String> arrayList, int i) {
        if (arrayList.size() <= 0) {
            return true;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (ContextCompat.checkSelfPermission(this, arrayList.get(i2)) != 0) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        if (arrayList2.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList2.toArray(new String[arrayList2.size()]), i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ChoosePdf() {
        Intent intent = new Intent(this, (Class<?>) FilePickerActivity.class);
        intent.putExtra(FilePickerConstants.FILE, true);
        intent.putExtra(FilePickerConstants.MULTIPLE_TYPES, new String[]{FilePickerConstants.MIME_PDF});
        startActivityForResult(intent, this.ATTACHMENT_CHOICE_TASK_TAKE_PHOTO_for10);
    }
}
