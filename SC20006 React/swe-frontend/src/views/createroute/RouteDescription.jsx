import React, { useState } from "react";
import smile from "../../assets/smile.svg";
import star from "../../assets/star.svg";
import sun from "../../assets/sun.svg";
import BottomDrawerButton from "../../assets/BottomDrawerButton.svg";
import { useSearchParams } from "react-router-dom";
import { Transition } from "@headlessui/react";

const SmallBox = ({ children, className }) => {
  return (
    <button
      className={`flex flex-col gap-[10px] py-[4px] mt-[10px] rounded-[10px] w-full px-[16px] text-[20px] text-[#0c0c0c] leading-[23px] box-border hover:border hover:py-[3px] hover:px-[15px] ${className}`}
    >
      {children}
    </button>
  );
};

const SmallButton = ({ children, className, onClick }) => {
  return (
    <button
      className={`mt-[8px] w-max text-[15px] text-[#2E57A7] hover:underline rounded-[4px] ${className}`}
      onClick={onClick}
    >
      {children}
    </button>
  );
};

const RouteDescription = ({ routeDistance, onSave, shrinkMobileDrawer, expandMobileDrawer }) => {
  const [showDrawer, setShowDrawer] = useState(false);
  const [searchParams,setSearchParams] = useSearchParams();
  const mode = searchParams.get("mode");
  return (
    <>
      {/* Desktop Route Description */}
      <div className="flex-col m-[10px] w-[387px] h-[calc(100%-20px)] px-[12px] py-[13px] bg-[#918d8db4] p-[20px] rounded-[10px] overflow-hidden hidden sm:flex">
        {/* <div className="flex flex-row items-center gap-[10px] flex-grow-8 bg-white m-[10px] rounded-[10px] text-black text-[20px] p-[10px]">
          <img src={star} className="self-end h-[40px]" />
          <p>New route created!</p>
        </div> */}
        <div className="flex flex-col flex-grow bg-white m-[10px] rounded-[10px]  text-black text-[20px] p-[16px]">
          <p>Route Details</p>
          <SmallBox className="bg-[#98bdfc81]">
            <div className="flex flex-row justify-between">
              <img src={sun} className="mt-[10px] mb-[10px]" />
              <div className="flex flex-col items-end">
                <h1 className="mt-[25px] text-[43px] text-[#565150] self-end">
                  29 Aug
                </h1>
                <p className="mt-[20px] text-[#565150] text-[24px] self-end">
                  7:00 am
                </p>
                <p className="mt-[10px] mb-[10px] text-[#565150] text-[24px] self-end">
                  Sunny | 32°C
                </p>
              </div>
            </div>
          </SmallBox>
          <SmallBox className="bg-[#bbdd8578]">
            <div className="flex flex-row gap-[25px] items-center">
              <p className="text-[#565150] text-[20px]">
                PM 2.5 Index: 30 µg/m³
              </p>
              <div className="mt-[5px]">
                <img src={smile} className="self-end h-[35px]" />
                <p className="mt-auto self-end text-[12px] text-[#565150]">
                  Normal
                </p>
              </div>
            </div>
          </SmallBox>
          <SmallBox className="bg-[#ddd48576]">
            <p className="text-[#565150] mt-[10px] mb-[10px]">
              Route distance: {(routeDistance / 1000).toFixed(2)} km
            </p>
          </SmallBox>
          <div className="flex justify-between mt-auto">
            <SmallButton onClick={() => setSearchParams({page: "0", mode})}>Edit route</SmallButton>
            <SmallButton onClick={onSave}>Cycle route</SmallButton>
          </div>
        </div>
      </div>
      {/* Mobile & Tablet Route Description */}
      <Transition
       className={`w-full bg-white text-[#565150] h-full absolute bottom-0 rounded-t-[10px] flex sm:hidden flex-col px-[15px] pt-[22px] pb-[10px] font-medium gap-[11px] z-10`} 
       show={showDrawer}
       enter="transition-all duration-500 ease-in-out"
       enterFrom="translate-y-[355px]"
       enterTo="translate-y-0"
       leave="transition-all duration-500 ease-in-out"
       leaveFrom="translate-y-0"
       leaveTo="translate-y-[355px]"
       onTouchStart={() => setShowDrawer(!showDrawer)}
       beforeEnter={expandMobileDrawer}
       afterLeave={shrinkMobileDrawer}
      >
        <img
          src={BottomDrawerButton}
          className="w-[26px] absolute left-1/2 -translate-x-1/2 -translate-y-[6px]"
        />
        <h1 className="text-[22px] text-black leading-[26px]">Route Details</h1>
        <div className="bg-[#98BDFC81] pt-[14px] w-full pb-[20px] rounded-[10px] pl-[9px] pr-[33px] flex justify-between">
          <img src={sun} className="w-[97px] h-[97px]" />
          <div className="flex flex-col items-end">
            <p className="text-[36px] leading-[42px]">29 Aug</p>
            <p className="mt-[4px] text-[24px] leading-[28px]">7:00 am</p>
            <div className="h-[40px] flex justify-center items-center mt-[5px] text-[24px]">
              <div className="h-full border-r-[3px] border-[#565150] pr-[9px] flex items-center">
                Sunny
              </div>
              <p className="ml-[9px]">32°C</p>
            </div>
          </div>
        </div>
        <div className="bg-[#BCDD8581] w-full h-[56px] rounded-[10px] flex justify-between items-center pl-[17px] pr-[10px]">
          <p className="text-[18px]">
            PM 2.5 Index: 30 µg/m<sup>3</sup>
          </p>
          <div className="flex flex-col items-center">
            <img src={smile} />
            <p className="text-[15px] leading-[16px]">Normal</p>
          </div>
        </div>
        <div className="bg-[#DDD48581] rounded-[10px] w-full h-[56px] flex items-center pl-[17px] text-[18px]">
          <p>Route distance: {(routeDistance / 1000).toFixed(2)} km</p>
        </div>
        <div className="mt-auto text-[#2E57A7] text-[22px] w-full flex justify-between">
          <p className="hover:underline decoration-[#2E57A7]" onClick={(e) => {e.stopPropagation(); setSearchParams({page: "0", mode})}}>Edit route</p>
          <p className="hover:underline decoration-[#2E57A7]" onClick={onSave}>Cycle route</p>
        </div>
      </Transition>
      <div className="w-full h-[59px] bg-white absolute bottom-0 rounded-t-[10px] flex sm:hidden flex-col px-[15px] pt-[22px] pb-[10px] font-medium z-0" onTouchStart={() => setShowDrawer(!showDrawer)}>
        <img
          src={BottomDrawerButton}
          className="w-[26px] absolute left-1/2 -translate-x-1/2 -translate-y-[6px]"
        />
        <h1 className="text-[22px] text-black leading-[26px]">Route Details</h1>
      </div>
    </>
  );
};

export default RouteDescription;
