package bachelor.fmi.uni.myfirstapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    EditText filter;
    Button addButton;
    ListView list;
    EditText itemName;

    Object itemForEdit;

    ArrayList<String> shoppingItems = new ArrayList();
    ArrayAdapter<String> adapter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        int color = getIntent().getIntExtra("color", Color.WHITE);

        filter = (EditText) findViewById(R.id.filterEditText);
        addButton = (Button) findViewById(R.id.addNewItemButton);
        list = (ListView) findViewById(R.id.shoppingListView);

        shoppingItems.add("Coffee");
        shoppingItems.add("Rum");
        shoppingItems.add("Tea");

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, shoppingItems);
        list.setAdapter(adapter);
        addButton.setOnClickListener(addButtonClicked);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Object clickedItem = list.getItemAtPosition(position);

                dialog = new Dialog(ShoppingListActivity.this);
                dialog.setContentView(R.layout.action_layout);
                ListView actionList = (ListView) dialog.findViewById(R.id.actionListView);
                ArrayAdapter<String> actionAdapter = new ArrayAdapter<String>(
                        ShoppingListActivity.this, android.R.layout.simple_list_item_1,
                        new String[]{"Изтрий", "Редактрирай"}
                );

                actionList.setAdapter(actionAdapter);

                dialog.show();

                actionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog.cancel();//трябва да е най-отгоре за да не ни затваря втория диалогов прозорец

                        switch (position) {
                            case 0:
                                shoppingItems.remove(clickedItem);
                                adapter.notifyDataSetChanged();
                                break;
                            case 1:
                                addEditArticle(clickedItem);
                                break;
                        }


                    }
                });

                return true;//определя дали да изпълни следващите събития по веригата
            }
        });
    }

    private void addEditArticle(Object item) {
        dialog = new Dialog(ShoppingListActivity.this);
        dialog.setContentView(R.layout.add_item_layout);

        itemName = (EditText) dialog.findViewById(
                R.id.articleNameEditText);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        Button cancelButton = (Button) dialog.findViewById(
                R.id.cancelButton);

        okButton.setOnClickListener(okCancelButton);
        cancelButton.setOnClickListener(okCancelButton);

        if (item != null) {
            itemName.setText(item.toString());
            itemForEdit = item;
        }
        dialog.show();
    }


    View.OnClickListener addButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addEditArticle(null);
        }
    };

    View.OnClickListener okCancelButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.okButton: {
                    if (itemForEdit != null) {
                        shoppingItems.remove(itemForEdit);
                        shoppingItems.add(itemName.getText().toString());
                        itemForEdit = null;
                    } else {
                        shoppingItems.add(itemName.getText().toString());
                    }

                    adapter.notifyDataSetChanged();
                    dialog.cancel();
                }
                break;
                case R.id.cancelButton: {
                    dialog.cancel();
                }
                break;
            }
        }
    };
}
