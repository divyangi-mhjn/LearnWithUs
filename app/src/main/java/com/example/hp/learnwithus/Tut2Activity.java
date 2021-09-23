package com.example.hp.learnwithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tut2Activity extends AppCompatActivity {
    DatabaseReference ref1;
    ArrayList<Tut2> tut2;
    ListView listViewtut2;
    String value;
    TextView value1,value2,value3,value4,value5,value6;
    String history,features,path,jvm,operator,variable,ifElse,switchh,forr,whilee,breakk,comments,array1,typess,declar,names,matrices,examples;
    String oop,naming,constructor,staticc,thiss,objectt,inh,over,rid,sup,run,ins,abst,inte,ver,pack,access,enca,introd,tr,thr,fina,thro,handle,introduce,lifecycle;
    String create,thread,name,daemon,inp,inps,bos,fp,wr,writ,ntww,sock,url,hrl,ht,datag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tut2_list);

        ref1=FirebaseDatabase.getInstance().getReference("tut2");



        tut2 = new ArrayList<>();
        Bundle b = getIntent().getExtras();
        value= b.getString("value");
        value1 = (TextView) findViewById(R.id.value1);
        value2 = (TextView) findViewById(R.id.value2);
        value3 = (TextView) findViewById(R.id.value3);
        value4 = (TextView) findViewById(R.id.value4);
        value5 = (TextView) findViewById(R.id.value5);
        value6 = (TextView)findViewById(R.id.value6);

        history="history";
        features="features";
        path="path";
        jvm="jvm";
        operator="operator";
        variable="variable";
        ifElse="ifElse";
        switchh="switchh";
        forr="forr";
        whilee="whilee";
        breakk="breakk";
        comments="comments";
        array1="array1";
        declar="declar";
        typess="typess";
        names="names";
        matrices="matrices";
        examples="examples";
        oop="oop";
        naming="naming";
        constructor="constructor";
        staticc="staticc";
        thiss="thiss";
        objectt="objectt";
        inh="inh";
        over="over";
        rid="rid";
        sup="sup";
        run="run";
        ins="ins";
        abst="abst";
        inte="inte";
        ver="ver";
        pack="pack";
        access="access";
        enca="enca";
        introd="introd";
        tr="tr";
        thr="thr";
        fina="fina";
        thr="thr";
        handle="handle";
        introduce="introduce";
        lifecycle="lifecycle";
        create="create";
        thread="thread";
        name="name";
        daemon="daemon";
        inp="inp";
        inps="inps";
        bos="bos";
        fp="fp";
        wr="wr";
        writ="writ";
        ntww="ntww";
        sock="sock";
        url="url";
        hrl="hrl";
        ht="ht";
        datag="datag";
    }
    @Override
    protected void onStart() {
        super.onStart();

        //attaching value event listener

  if(value.equals("basic")){


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    tut2.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        Tut2 tut21 = postSnapshot.child("basic").getValue(Tut2.class);

                        value1.setText(tut21.getValue1());
                        value2.setText(tut21.getValue2());
                        value3.setText(tut21.getValue3());
                        value4.setText(tut21.getValue4());
                        value5.setText(tut21.getValue5());
                        value6.setText(tut21.getValue6());
                    }
                    value1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",history);

                            startActivity(intent);
                        }
                    });
                    value2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",features);

                            startActivity(intent);
                        }
                    });
                    value3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",path);

                            startActivity(intent);
                        }
                    });
                    value4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",jvm);

                            startActivity(intent);
                        }
                    });
                    value5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",operator);

                            startActivity(intent);
                        }
                    });
                    value6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                            intent.putExtra("value",variable);

                            startActivity(intent);
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });}
        else if(value.equals("loops")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                  Tut2 tut21 = postSnapshot.child("loops").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",ifElse);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",switchh);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",forr);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",whilee);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",breakk);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",comments);

                      startActivity(intent);
                  }
              });


          }


          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });

  }
  else if(value.equals("array")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  Tut2 tut21 = postSnapshot.child("array").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",array1);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",typess);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",declar);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",names);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",matrices);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",examples);

                      startActivity(intent);
                  }
              });


          }

          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }

  else if(value.equals("object")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("object").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",oop);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",naming);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",constructor);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",staticc);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",thiss);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",objectt);

                      startActivity(intent);
                  }
              });


          }


          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }
  else if(value.equals("inher")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("inher").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",inh);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",over);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",rid);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",sup);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",run);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",ins);

                      startActivity(intent);
                  }
              });


          }




          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }

  else if(value.equals("abs")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("abs").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",abst);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",inte);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",ver);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",pack);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",access);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",enca);

                      startActivity(intent);
                  }
              });


          }

          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }

  else if(value.equals("exc")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("exc").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",introd);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",tr);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",thr);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",fina);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",thr);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",handle);

                      startActivity(intent);
                  }
              });


          }



          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }
  else if(value.equals("multi")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("multi").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",introduce);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",lifecycle);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",create);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",thread);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",name);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",daemon);

                      startActivity(intent);
                  }
              });


          }



          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }
  else if(value.equals("io")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("io").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",inp);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",inps);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",bos);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",fp);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",wr);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",writ);

                      startActivity(intent);
                  }
              });


          }



          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }
  else if(value.equals("ntw")) {


      ref1.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              tut2.clear();

              //iterating through all the nodes
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                  //getting artist
                  Tut2 tut21 = postSnapshot.child("ntw").getValue(Tut2.class);
                  value1.setText(tut21.getValue1());
                  value2.setText(tut21.getValue2());
                  value3.setText(tut21.getValue3());
                  value4.setText(tut21.getValue4());
                  value5.setText(tut21.getValue5());
                  value6.setText(tut21.getValue6());
              }
              value1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",ntww);

                      startActivity(intent);
                  }
              });
              value2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",sock);

                      startActivity(intent);
                  }
              });
              value3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",url);

                      startActivity(intent);
                  }
              });
              value4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",hrl);

                      startActivity(intent);
                  }
              });
              value5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",ht);

                      startActivity(intent);
                  }
              });
              value6.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent=new Intent(Tut2Activity.this,TutorialFinal.class);
                      intent.putExtra("value",datag);

                      startActivity(intent);
                  }
              });

          }


          @Override
          public void onCancelled(DatabaseError databaseError) {


          }
      });
  }
        }
    @Override
    public void onBackPressed() {
       finish();

    }



}
