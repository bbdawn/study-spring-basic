package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    //AppConfig
    //애플리케이션의 전체 동작방식을 구성(config) 하기 위해, 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
    //애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다
    //생성 가능한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다

    //MemberServiceImpl 입장에서 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다
    //MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다
    //MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다

    //AppConfig를 통해서 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확하게 분리되었다.

//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }


    //위 코드 리팩토링. 역할과 구현 클래스가 한눈에 들어오도록.
    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    private static MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        할인정책을 Fix에서 Rate로 바꾸고 싶다면 아래 코드만 바꾸면 됨!
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
