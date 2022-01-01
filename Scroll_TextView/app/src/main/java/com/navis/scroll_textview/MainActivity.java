package com.navis.scroll_textview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textTitle = null;
    TextView textSubtitle = null;
    TextView textContent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textContent = findViewById(R.id.textContent);
        registerForContextMenu(textTitle);
        textSubtitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        getMenuInflater().inflate(R.menu.main_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        Toast t = null;
                        switch (menuItem.getItemId())
                        {
                            case R.id.menuCopy:
                                t = Toast.makeText(MainActivity.this, "Copy", Toast.LENGTH_LONG);
                                t.show();
                                break;
                            case R.id.menuEdit:
                                t = Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_LONG);
                                t.show();
                                break;
                            case R.id.menuPaste:
                                t = Toast.makeText(MainActivity.this, "Paste", Toast.LENGTH_LONG);
                                t.show();
                                break;
                            case R.id.menuShare:
                                t = Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_LONG);
                                t.show();
                                break;
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                });

                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast t = null;
        switch (item.getItemId())
        {
            case R.id.menuCopy:
                t = Toast.makeText(this, "Copy", Toast.LENGTH_LONG);
                t.show();
                break;
            case R.id.menuEdit:
                t = Toast.makeText(this, "Edit", Toast.LENGTH_LONG);
                t.show();
                break;
            case R.id.menuPaste:
                final MyDatePickerDialog datePickerDialog = new MyDatePickerDialog(MainActivity.this);
                datePickerDialog.show();

                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Toast t = Toast.makeText(MainActivity.this, Integer.toString(datePickerDialog.resultDoM), Toast.LENGTH_LONG);
                        t.show();
                    }
                });
                break;
            case R.id.menuShare:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Sharing Confirmation");
                dialog.setMessage("Are you sure to share this content?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast t = null;
                        t = Toast.makeText(MainActivity.this, "Share OK", Toast.LENGTH_LONG);
                        t.show();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast t = null;
                        t = Toast.makeText(MainActivity.this, "Share CANCEL", Toast.LENGTH_LONG);
                        t.show();
                    }
                });
                dialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
