package com.navis.tabexample2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

public class Tab1Fragment extends Fragment {
    RecyclerView recyclerView = null;
    LinkedList<String> listData = null;
    LinkedList<String> listTitle = null;
    LinkedList<String> listThumb = null;
    MyAdapter myAdapter = null;
    public Tab1Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        listData = new LinkedList<>();
        listTitle = new LinkedList<>();
        listThumb = new LinkedList<>();
        listTitle.add("Putin bác cáo buộc của Trump về gia đình Biden");
        listTitle.add("Ant Group được định giá 310 tỷ USD");
        listTitle.add("Đại biểu nghi ngại nhiều doanh nghiệp câu kết nâng giá thiết bị y tế");
        listTitle.add("Hà Anh Tuấn trồng cây gây rừng");
        listTitle.add("Đông Nhi sinh con gái");
        listTitle.add("Khách sạn sẽ ra sao hậu Covid-19?");
        listTitle.add("Phun trào núi lửa góp phần xóa sổ 95% sự sống");
        listTitle.add("Người ở nhờ vay nợ, gia chủ bị tạt sơn khắp nhà");
        listTitle.add("Tìm lỗi sai trong tranh");
        listTitle.add("Nhạc sĩ Văn Ký sáng tác đến cuối đời");
        listTitle.add("Chạy đêm hiệu quả theo cách của Pacer");
        listTitle.add("Melbourne sắp kết thúc đợt phong tỏa lâu nhất thế giới");
        listData.add("Putin nói ông không thấy dấu hiệu phạm tội trong các mối quan hệ kinh doanh giữa gia đình Biden với Nga hay Ukraine, thể hiện bất đồng với Trump.");
        listData.add("Với mức giá chào bán vừa công bố, Ant Group dự kiến thu về hơn 34 tỷ USD từ IPO, đồng thời lập kỷ lục thế giới.");
        listData.add("Đại biểu Nguyễn Bá Sơn đề nghị điều tra thông tin việc 5 doanh nghiệp nhập khẩu và phân phối thiết bị y tế cùng hình thành mặt bằng giá cắt cổ.");
        listData.add("Hà Anh Tuấn chi tiền túi trồng cây tại Đà Nẵng và Lâm Đồng, hoạt động thuộc dự án \"Rừng Việt Nam\" do anh khởi xướng.");
        listData.add("Ca sĩ Đông Nhi sinh con gái đầu lòng nặng 3,06 kg, đặt tên thân mật Winnie, tối 26/10.");
        listData.add("Phòng di động, điều khiển không tiếp xúc, robot phục vụ là vài ý tưởng các nhà thiết kế khách sạn đang xem xét cho ngành du lịch hậu Covid-19. ");
        listData.add("Các vụ phun trào núi lửa ở khu vực Siberia ngày nay đi kèm lượng khí methane lớn từ đáy biển, dẫn tới một trong những sự kiện tuyệt chủng lớn nhất trên Trái Đất.");
        listData.add("Căn nhà trung tâm quận 1 liên tiếp bị tạt sơn không lý do, cảnh sát vào cuộc điều tra xác định người bạn ở nhờ đã vay tiền giang hồ không trả.");
        listData.add("Điểm bất hợp lý được gài khéo léo trong mỗi bức tranh về giao thông hay đồ vật, bạn cần quan sát thật kỹ và tìm ra trong 10 giây.");
        listData.add("Vài tháng trước khi mất, nhạc sĩ Văn Ký phổ nhạc bài \"Covid phải lùi xa\", cổ vũ tinh thần người dân Việt Nam giữa đại dịch.");
        listData.add("Một chiếc khăn quanh cổ để giữ ấm hay không \"núp gió\" quá gần để tránh ổ gà là hai trong số những lời khuyên Pacer dành cho người chạy giải VM Hanoi Midnight.");
        listData.add("Thành phố Melbourne sẽ kết thúc đợt phong tỏa dài 112 ngày sau khi lần đầu tiên không ghi nhận ca nhiễm nCoV mới kể từ tháng 7. ");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/26/fbcapdoidownload-1603672544-1691-1603672576.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=ig5U0OFIN5Sqiy-JVuCr-w");
        listThumb.add("https://i1-kinhdoanh.vnecdn.net/2020/10/26/antgroup-1603718610-1883-1603718634.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=4PlaycjRvO_gzHTqvbpvLg");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/26/201911141802283296NguyenBaSono-8243-7291-1603699091.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=pZ7lOSYuioxuei4XK1LGsg");
        listThumb.add("https://i1-giaitri.vnecdn.net/2020/10/26/tuan-1603695626-1990-1603695773.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=WUcwMHKKWYLCuoKtvymESg");
        listThumb.add("https://i1-giaitri.vnecdn.net/2020/10/26/dongnhitop-1603700313-9210-1603708956.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=aXdru_ln1vXu1JkY1iVoEA");
        listThumb.add("https://i1-dulich.vnecdn.net/2020/10/26/robotphucvukhachsan-1603697750-7414-9882-1603697865.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=5lqkCf0L3l9Ea5eEhDkCrg");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/26/VNEMass-1603703175-9886-1603703236.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=e9MDhQSptjQqR9P19LtLKQ");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/26/TATSONQ1-2-3169-1603713782.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=c0x0OVmrMsNZ3op0-nFc1g");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/25/cau-5-1603616102-4198-1603616129.png?w=900&h=0&q=100&dpr=1&fit=crop&s=j3r1ChXRdqfj70_REDfiuQ");
        listThumb.add("https://i1-giaitri.vnecdn.net/2020/10/26/nhacsivanky2-1603716589-1758-1603716848.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=f-ZAE1CWQXMsZ--G38JKjA");
        listThumb.add("https://i1-thethao.vnecdn.net/2020/10/26/LVT3224JPG-1603710052-8256-1603710088.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=hHiH8aOiazv93J0RFGUX-g");
        listThumb.add("https://i1-vnexpress.vnecdn.net/2020/10/26/melbourne2-1603715446-5390-1603715588.jpg?w=900&h=0&q=100&dpr=1&fit=crop&s=QUnKnYJBn7OKILweo4TlPw");

        myAdapter = new MyAdapter(getContext(), listTitle, listData, listThumb);
        recyclerView.setAdapter(myAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
    }
}
